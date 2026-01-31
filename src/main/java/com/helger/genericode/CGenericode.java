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

import java.util.List;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.CodingStyleguideUnaware;
import com.helger.annotation.style.PresentForCodeCoverage;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.io.resource.ClassPathResource;
import com.helger.xsds.xml.CXML_XSD;

/**
 * Constants for the handling of Genericode documents
 *
 * @author Philip Helger
 */
@Immutable
public final class CGenericode
{
  public static final String GENERICODE_04_NAMESPACE_URI = "http://genericode.org/2006/ns/CodeList/0.4/";
  public static final String GENERICODE_10_NAMESPACE_URI = "http://docs.oasis-open.org/codelist/ns/genericode/1.0/";

  @NonNull
  private static ClassLoader _getCL ()
  {
    return CGenericode.class.getClassLoader ();
  }

  private static final String PREFIX = "external/schemas/";

  /** 0.4 XSD resources */
  @CodingStyleguideUnaware
  public static final List <ClassPathResource> GENERICODE_04_XSDS = new CommonsArrayList <> (CXML_XSD.getXSDResource (),
                                                                                             new ClassPathResource (PREFIX +
                                                                                                                    "genericode-code-list-0.4.xsd",
                                                                                                                    _getCL ())).getAsUnmodifiable ();

  /** 1.0 XSD resources */
  @CodingStyleguideUnaware
  public static final List <ClassPathResource> GENERICODE_10_XSDS = new CommonsArrayList <> (CXML_XSD.getXSDResource (),
                                                                                             new ClassPathResource (PREFIX +
                                                                                                                    "genericode-1.0.xsd",
                                                                                                                    _getCL ())).getAsUnmodifiable ();

  @PresentForCodeCoverage
  private static final CGenericode INSTANCE = new CGenericode ();

  private CGenericode ()
  {}
}
