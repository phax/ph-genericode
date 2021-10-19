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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.jaxb.builder.JAXBValidationBuilder;

/**
 * A validation builder for Genericode documents.
 *
 * @author Philip Helger
 * @param <JAXBTYPE>
 *        The Genericode implementation class to be read
 */
@NotThreadSafe
public class GenericodeValidator <JAXBTYPE> extends JAXBValidationBuilder <JAXBTYPE, GenericodeValidator <JAXBTYPE>>
{
  public GenericodeValidator (@Nonnull final EGenericodeDocumentType eDocType)
  {
    super (eDocType);
  }

  /**
   * Create a validator builder for com.helger.genericode.v04.CodeListDocument.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static GenericodeValidator <com.helger.genericode.v04.CodeListDocument> gc04CodeList ()
  {
    return new GenericodeValidator <> (EGenericodeDocumentType.GC04_CODE_LIST);
  }

  /**
   * Create a validator builder for
   * com.helger.genericode.v04.CodeListSetDocument.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static GenericodeValidator <com.helger.genericode.v04.CodeListSetDocument> gc04CodeListSet ()
  {
    return new GenericodeValidator <> (EGenericodeDocumentType.GC04_CODE_LIST_SET);
  }

  /**
   * Create a validator builder for com.helger.genericode.v04.ColumnSetDocument.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static GenericodeValidator <com.helger.genericode.v04.ColumnSetDocument> gc04ColumnSet ()
  {
    return new GenericodeValidator <> (EGenericodeDocumentType.GC04_COLUMN_SET);
  }

  /**
   * Create a validator builder for com.helger.genericode.v10.CodeListDocument.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static GenericodeValidator <com.helger.genericode.v10.CodeListDocument> gc10CodeList ()
  {
    return new GenericodeValidator <> (EGenericodeDocumentType.GC10_CODE_LIST);
  }

  /**
   * Create a validator builder for
   * com.helger.genericode.v10.CodeListSetDocument.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static GenericodeValidator <com.helger.genericode.v10.CodeListSetDocument> gc10CodeListSet ()
  {
    return new GenericodeValidator <> (EGenericodeDocumentType.GC10_CODE_LIST_SET);
  }

  /**
   * Create a validator builder for com.helger.genericode.v10.ColumnSetDocument.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static GenericodeValidator <com.helger.genericode.v10.ColumnSetDocument> gc10ColumnSet ()
  {
    return new GenericodeValidator <> (EGenericodeDocumentType.GC10_COLUMN_SET);
  }
}
