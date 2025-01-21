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

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.xsds.xml.CXML_XSD;

/**
 * Constants for the handling of ContextValueAssociation documents
 *
 * @author Philip Helger
 */
@Immutable
public final class CCVA
{
  public static final String CVA_10_NAMESPACE_URI = "http://docs.oasis-open.org/codelist/ns/ContextValueAssociation/1.0/";

  @Nonnull
  private static ClassLoader _getCL ()
  {
    return CCVA.class.getClassLoader ();
  }

  private static final String PREFIX = "external/schemas/";

  /** 1.0 XSD resources */
  @CodingStyleguideUnaware
  public static final List <ClassPathResource> CVA_10_XSDS = new CommonsArrayList <> (CXML_XSD.getXSDResource (),
                                                                                      new ClassPathResource (PREFIX +
                                                                                                             "ContextValueAssociation-1.0.xsd",
                                                                                                             _getCL ())).getAsUnmodifiable ();

  @PresentForCodeCoverage
  private static final CCVA s_aInstance = new CCVA ();

  private CCVA ()
  {}
}
