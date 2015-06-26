/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.helger.commons.io.file.iterate.FileSystemIterator;
import com.helger.commons.io.resource.FileSystemResource;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.xml.serialize.read.DOMReader;
import com.helger.genericode.v10.CodeListDocument;

/**
 * Test class for class {@link Genericode10CodeListMarshaller}.
 *
 * @author Philip Helger
 */
public final class Genericode10CodeListMarshallerTest
{
  private static void _testReadAndWriteValid (final IReadableResource aRes) throws SAXException
  {
    // Resolve resource
    assertTrue (aRes.exists ());

    // Read XML
    final Document aDoc = DOMReader.readXMLDOM (aRes);
    assertNotNull (aDoc);

    // Read code list
    final CodeListDocument aCLDoc = new Genericode10CodeListMarshaller ().read (aDoc);
    assertNotNull (aCLDoc);

    // Write again
    final Document aDoc2 = new Genericode10CodeListMarshaller ().write (aCLDoc);
    assertNotNull (aDoc2);
  }

  @Test
  public void testReadValid () throws SAXException
  {
    for (final File aFile : new FileSystemIterator ("src/test/resources/examples/gc/v10"))
      if (aFile.isFile ())
        _testReadAndWriteValid (new FileSystemResource (aFile));
  }
}
