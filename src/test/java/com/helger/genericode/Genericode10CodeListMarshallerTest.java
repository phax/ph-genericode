/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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

import org.jspecify.annotations.NonNull;
import org.junit.Test;
import org.w3c.dom.Document;

import com.helger.genericode.v10.CodeListDocument;
import com.helger.io.file.FileSystemIterator;
import com.helger.io.file.IFileFilter;
import com.helger.io.resource.FileSystemResource;
import com.helger.io.resource.IReadableResource;
import com.helger.unittest.support.TestHelper;
import com.helger.xml.serialize.read.DOMReader;

/**
 * Test class for class {@link Genericode10CodeListMarshaller}.
 *
 * @author Philip Helger
 */
public final class Genericode10CodeListMarshallerTest
{
  private static void _testReadAndWriteValid (@NonNull final IReadableResource aRes)
  {
    // Resolve resource
    assertTrue (aRes.exists ());

    // Read XML
    final Document aDoc = DOMReader.readXMLDOM (aRes);
    assertNotNull (aRes.getPath (), aDoc);

    final Genericode10CodeListMarshaller aMarshaller = new Genericode10CodeListMarshaller ();

    // Read code list
    final CodeListDocument aCLDoc = aMarshaller.read (aDoc);
    assertNotNull (aRes.getPath (), aCLDoc);

    // Write again
    final Document aDoc2 = aMarshaller.getAsDocument (aCLDoc);
    assertNotNull (aRes.getPath (), aDoc2);

    // Read code list again
    final CodeListDocument aCLDoc2 = aMarshaller.read (aDoc2);
    assertNotNull (aRes.getPath (), aCLDoc2);
    TestHelper.testDefaultImplementationWithEqualContentObject (aCLDoc, aCLDoc2);
    TestHelper.testDefaultImplementationWithEqualContentObject (aCLDoc, aCLDoc.clone ());
  }

  @Test
  public void testReadValid ()
  {
    for (final File aFile : new FileSystemIterator ("src/test/resources/external/examples/gc/v10").withFilter (IFileFilter.filenameEndsWith (".gc")))
      _testReadAndWriteValid (new FileSystemResource (aFile));
  }
}
