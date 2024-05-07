/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.genericode;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Nonnull;

import com.helger.genericode.v10.Row;
import com.helger.genericode.v10.SimpleCodeList;
import com.helger.xml.namespace.MapBasedNamespaceContext;
import org.junit.Test;
import org.w3c.dom.Document;

import com.helger.commons.io.file.FileSystemIterator;
import com.helger.commons.io.file.IFileFilter;
import com.helger.commons.io.resource.FileSystemResource;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.mock.CommonsTestHelper;
import com.helger.genericode.v10.CodeListDocument;
import com.helger.xml.serialize.read.DOMReader;

/**
 * Test class for class {@link Genericode10CodeListMarshaller}.
 *
 * @author Philip Helger
 */
public final class Genericode10EN16931CodeListMarshallerTest
{
  private static void _testReadAndWriteValid (@Nonnull final IReadableResource aRes)
  {
      // Resolve resource
      assertTrue (aRes.exists ());

      // Read XML
      final Document aDoc = DOMReader.readXMLDOM (aRes);
      assertNotNull (aRes.getPath(), aDoc);
      final Genericode10CodeListMarshaller aMarshaller = new Genericode10CodeListMarshaller ();

      // Read code list
      final CodeListDocument aCLDoc = aMarshaller.read (aDoc);
      assertNotNull (aRes.getPath(), aCLDoc);
      SimpleCodeList simpleCodeList = aCLDoc.getSimpleCodeList();
      /**
       <Row>
       <Value ColumnRef="Code">
       <SimpleValue>AFN</SimpleValue>
       </Value>
       <Value ColumnRef="Name">
       <SimpleValue>Afghani</SimpleValue>
       </Value>
       </Row>
       */
      List<Row> rows = simpleCodeList.getRow();
      rows.sort(new Comparator<Row>() {
        @Override
        public int compare(Row lhs, Row rhs){
          // get SOME_CODE the content of  <Value ColumnRef="Code"><SimpleValue>SOME_CODE</SimpleValue>
          String lhsCode = Genericode10Helper.getRowValue (lhs, "Code");
          String rhsCode = Genericode10Helper.getRowValue (rhs, "Code");
          try{
              // parseInt("Kona", 10) throws a NumberFormatException
              // parseInt("Kona", 27) returns 411787
              // using Character.MAX_RADIX == 36
              return Integer.valueOf(Integer.parseInt(lhsCode, 36)).compareTo(Integer.valueOf(Integer.parseInt(rhsCode, 36)));
          }catch(NumberFormatException e){
              return lhsCode.compareTo(rhsCode);
          }
        }
      });

      // Write again
      final MapBasedNamespaceContext aNSContext = new MapBasedNamespaceContext ();
      aNSContext.addMapping ("gc", CGenericode.GENERICODE_10_NAMESPACE_URI);
      aNSContext.addDefaultNamespaceURI ("");
      aMarshaller.setNamespaceContext(aNSContext);
      aMarshaller.setFormattedOutput(true);
      String fileName = aRes.getPath().substring(aRes.getPath().lastIndexOf(File.separatorChar), aRes.getPath().length());
      aMarshaller.write(aCLDoc, Paths.get("target" + File.separator + "generated-test-sources" + File.separator + fileName));

      final Document aDoc2 = aMarshaller.getAsDocument (aCLDoc);
      assertNotNull (aRes.getPath(), aDoc2);

      // Read code list again
      final CodeListDocument aCLDoc2 = aMarshaller.read (aDoc2);
      assertNotNull (aRes.getPath(), aCLDoc2);
      CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aCLDoc, aCLDoc2);
      CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aCLDoc, aCLDoc.clone ());
  }

  @Test
  public void testReadValid ()
  {
    for (final File aFile : new FileSystemIterator ("src/test/resources/external/examples/gc/v10/11_2023-05-15").withFilter (IFileFilter.filenameEndsWith (".gc")))
      _testReadAndWriteValid (new FileSystemResource (aFile));
  }
}
