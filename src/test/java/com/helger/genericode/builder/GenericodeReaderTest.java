/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.genericode.builder;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.w3c.dom.Document;

import com.helger.commons.io.file.FileSystemIterator;
import com.helger.commons.io.file.IFileFilter;
import com.helger.commons.io.resource.FileSystemResource;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.mock.CommonsTestHelper;
import com.helger.xml.serialize.read.DOMReader;

/**
 * Test class for class {@link GenericodeReader}.
 *
 * @author Philip Helger
 */
public final class GenericodeReaderTest
{
  private static void _testReadAndWrite04 (final IReadableResource aRes)
  {
    // Resolve resource
    assertTrue (aRes.exists ());

    // Read XML
    final Document aDoc = DOMReader.readXMLDOM (aRes);
    assertNotNull (aDoc);

    // Read code list
    final com.helger.genericode.v04.CodeListDocument aCLDoc = GenericodeReader.gc04CodeList ().read (aDoc);
    assertNotNull (aCLDoc);

    // Write again
    final Document aDoc2 = GenericodeWriter.gc04CodeList ().getAsDocument (aCLDoc);
    assertNotNull (aDoc2);

    // Read code list again
    final com.helger.genericode.v04.CodeListDocument aCLDoc2 = GenericodeReader.gc04CodeList ().read (aDoc2);
    assertNotNull (aRes.getPath (), aCLDoc2);
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aCLDoc, aCLDoc2);
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aCLDoc, aCLDoc.clone ());
  }

  @Test
  public void testReadWrite04 ()
  {
    for (final File aFile : new FileSystemIterator ("src/test/resources/examples/gc/v04"))
      if (aFile.isFile ())
        _testReadAndWrite04 (new FileSystemResource (aFile));
  }

  private static void _testReadAndWrite10 (@Nonnull final IReadableResource aRes)
  {
    // Resolve resource
    assertTrue (aRes.exists ());

    // Read XML
    final Document aDoc = DOMReader.readXMLDOM (aRes);
    assertNotNull (aDoc);

    // Read code list
    final com.helger.genericode.v10.CodeListDocument aCLDoc = GenericodeReader.gc10CodeList ().read (aDoc);
    assertNotNull (aCLDoc);

    // Write again
    final Document aDoc2 = GenericodeWriter.gc10CodeList ().getAsDocument (aCLDoc);
    assertNotNull (aDoc2);

    // Read code list again
    final com.helger.genericode.v10.CodeListDocument aCLDoc2 = GenericodeReader.gc10CodeList ().read (aDoc2);
    assertNotNull (aRes.getPath (), aCLDoc2);
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aCLDoc, aCLDoc2);
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aCLDoc, aCLDoc.clone ());
  }

  @Test
  public void testReadWrite10 ()
  {
    for (final File aFile : new FileSystemIterator ("src/test/resources/examples/gc/v10").withFilter (IFileFilter.filenameEndsWith (".gc")))
      if (aFile.isFile ())
        _testReadAndWrite10 (new FileSystemResource (aFile));
  }
}
