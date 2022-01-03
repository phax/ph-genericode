/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.jaxb.builder.JAXBWriterBuilder;
import com.helger.xml.namespace.MapBasedNamespaceContext;

/**
 * A writer builder for Genericode documents.
 *
 * @author Philip Helger
 * @param <JAXBTYPE>
 *        The Genericode implementation class to be read
 */
@NotThreadSafe
public class GenericodeWriter <JAXBTYPE> extends JAXBWriterBuilder <JAXBTYPE, GenericodeWriter <JAXBTYPE>>
{
  public GenericodeWriter (@Nonnull final EGenericodeDocumentType eDocType)
  {
    super (eDocType);

    // Create a special namespace context for the passed document type
    final MapBasedNamespaceContext aNSContext = new MapBasedNamespaceContext ();
    aNSContext.addMapping ("gc", m_aDocType.getNamespaceURI ());
    aNSContext.addDefaultNamespaceURI ("");
    setNamespaceContext (aNSContext);
  }

  /**
   * Create a writer builder for com.helger.genericode.v04.CodeListDocument.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static GenericodeWriter <com.helger.genericode.v04.CodeListDocument> gc04CodeList ()
  {
    return new GenericodeWriter <> (EGenericodeDocumentType.GC04_CODE_LIST);
  }

  /**
   * Create a writer builder for com.helger.genericode.v04.CodeListSetDocument.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static GenericodeWriter <com.helger.genericode.v04.CodeListSetDocument> gc04CodeListSet ()
  {
    return new GenericodeWriter <> (EGenericodeDocumentType.GC04_CODE_LIST_SET);
  }

  /**
   * Create a writer builder for com.helger.genericode.v04.ColumnSetDocument.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static GenericodeWriter <com.helger.genericode.v04.ColumnSetDocument> gc04ColumnSet ()
  {
    return new GenericodeWriter <> (EGenericodeDocumentType.GC04_COLUMN_SET);
  }

  /**
   * Create a writer builder for com.helger.genericode.v10.CodeListDocument.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static GenericodeWriter <com.helger.genericode.v10.CodeListDocument> gc10CodeList ()
  {
    return new GenericodeWriter <> (EGenericodeDocumentType.GC10_CODE_LIST);
  }

  /**
   * Create a writer builder for com.helger.genericode.v10.CodeListSetDocument.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static GenericodeWriter <com.helger.genericode.v10.CodeListSetDocument> gc10CodeListSet ()
  {
    return new GenericodeWriter <> (EGenericodeDocumentType.GC10_CODE_LIST_SET);
  }

  /**
   * Create a writer builder for com.helger.genericode.v10.ColumnSetDocument.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static GenericodeWriter <com.helger.genericode.v10.ColumnSetDocument> gc10ColumnSet ()
  {
    return new GenericodeWriter <> (EGenericodeDocumentType.GC10_COLUMN_SET);
  }
}
