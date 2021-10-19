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

import java.util.List;

import javax.annotation.Nonnull;
import javax.xml.validation.Schema;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.string.StringHelper;
import com.helger.genericode.CGenericode;
import com.helger.jaxb.builder.IJAXBDocumentType;
import com.helger.jaxb.builder.JAXBDocumentType;

/**
 * Enumeration with all available GeneriCode document types.
 *
 * @author Philip Helger
 */
public enum EGenericodeDocumentType implements IJAXBDocumentType
{
  GC04_CODE_LIST (com.helger.genericode.v04.CodeListDocument.class, CGenericode.GENERICODE_04_XSDS),
  GC04_CODE_LIST_SET (com.helger.genericode.v04.CodeListSetDocument.class, CGenericode.GENERICODE_04_XSDS),
  GC04_COLUMN_SET (com.helger.genericode.v04.ColumnSetDocument.class, CGenericode.GENERICODE_04_XSDS),
  GC10_CODE_LIST (com.helger.genericode.v10.CodeListDocument.class, CGenericode.GENERICODE_10_XSDS),
  GC10_CODE_LIST_SET (com.helger.genericode.v10.CodeListSetDocument.class, CGenericode.GENERICODE_10_XSDS),
  GC10_COLUMN_SET (com.helger.genericode.v10.ColumnSetDocument.class, CGenericode.GENERICODE_10_XSDS);

  private final JAXBDocumentType m_aDocType;

  private EGenericodeDocumentType (@Nonnull final Class <?> aClass, @Nonnull final List <? extends ClassPathResource> aXSDPaths)
  {
    m_aDocType = new JAXBDocumentType (aClass, aXSDPaths, x -> StringHelper.trimEnd (x, "Document"));
  }

  @Nonnull
  public Class <?> getImplementationClass ()
  {
    return m_aDocType.getImplementationClass ();
  }

  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  public ICommonsList <ClassPathResource> getAllXSDResources ()
  {
    return m_aDocType.getAllXSDResources ();
  }

  @Nonnull
  public String getNamespaceURI ()
  {
    return m_aDocType.getNamespaceURI ();
  }

  @Nonnull
  @Nonempty
  public String getLocalName ()
  {
    return m_aDocType.getLocalName ();
  }

  @Nonnull
  public Schema getSchema ()
  {
    return m_aDocType.getSchema ();
  }
}
