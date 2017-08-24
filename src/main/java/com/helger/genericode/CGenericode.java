/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.resource.IReadableResource;

/**
 * Constants for the handling of Genericode documents
 *
 * @author Philip Helger
 */
@Immutable
public final class CGenericode
{
  @Nonnull
  private static ClassLoader _getCL ()
  {
    return CGenericode.class.getClassLoader ();
  }

  /** 0.4 XSD resources */
  @CodingStyleguideUnaware
  public static final List <? extends IReadableResource> GENERICODE_04_XSDS = new CommonsArrayList <> (new ClassPathResource ("/schemas/genericode-code-list-0.4.xsd",
                                                                                                                              _getCL ()),
                                                                                                       new ClassPathResource ("/schemas/xml.xsd",
                                                                                                                              _getCL ())).getAsUnmodifiable ();

  /** 1.0 XSD resources */
  @CodingStyleguideUnaware
  public static final List <? extends IReadableResource> GENERICODE_10_XSDS = new CommonsArrayList <> (new ClassPathResource ("/schemas/genericode-1.0.xsd",
                                                                                                                              _getCL ()),
                                                                                                       new ClassPathResource ("/schemas/xml.xsd",
                                                                                                                              _getCL ())).getAsUnmodifiable ();

  @PresentForCodeCoverage
  private static final CGenericode s_aInstance = new CGenericode ();

  private CGenericode ()
  {}
}
