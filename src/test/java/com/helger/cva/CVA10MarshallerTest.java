/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.cva;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.w3c.dom.Document;

import com.helger.commons.io.file.FileSystemIterator;
import com.helger.commons.io.resource.FileSystemResource;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.cva.v10.ContextValueAssociation;
import com.helger.xml.serialize.read.DOMReader;

/**
 * Test class for class {@link CVA10Marshaller}.
 *
 * @author Philip Helger
 */
public final class CVA10MarshallerTest
{
  private static void _testReadAndWriteValid (@Nonnull final IReadableResource aRes)
  {
    // Resolve resource
    assertTrue (aRes.exists ());

    // Read XML
    final Document aDoc = DOMReader.readXMLDOM (aRes);
    assertNotNull (aDoc);

    // Read CVA
    final ContextValueAssociation aCVA = new CVA10Marshaller ().read (aDoc);
    assertNotNull (aCVA);

    // Write again
    final Document aDoc2 = new CVA10Marshaller ().getAsDocument (aCVA);
    assertNotNull (aDoc2);
  }

  @Test
  public void testReadValid ()
  {
    for (final File aFile : new FileSystemIterator ("src/test/resources/external/examples/cva/v10"))
      if (aFile.isFile ())
        _testReadAndWriteValid (new FileSystemResource (aFile));
  }
}
