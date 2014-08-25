/**
 * Copyright (C) 2006-2014 phloc systems (www.phloc.com)
 * Copyright (C) 2014 Philip Helger (www.helger.com)
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

import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotations.PresentForCodeCoverage;
import com.helger.commons.collections.ContainerHelper;
import com.helger.commons.io.IReadableResource;
import com.helger.commons.io.resource.ClassPathResource;

/**
 * Constants for the handling of Genericode documents
 * 
 * @author Philip Helger
 */
@Immutable
public final class CGenericode
{
  // 0.4 XSD resources
  public static final List <? extends IReadableResource> GENERICODE_04_XSDS = ContainerHelper.newUnmodifiableList (new ClassPathResource ("/schemas/genericode-code-list-0.4.xsd"),
                                                                                                                   new ClassPathResource ("/schemas/xml.xsd"));

  // 1.0 XSD resources
  public static final List <? extends IReadableResource> GENERICODE_10_XSDS = ContainerHelper.newUnmodifiableList (new ClassPathResource ("/schemas/genericode-1.0.xsd"),
                                                                                                                   new ClassPathResource ("/schemas/xml.xsd"));

  @SuppressWarnings ("unused")
  @PresentForCodeCoverage
  private static final CGenericode s_aInstance = new CGenericode ();

  private CGenericode ()
  {}
}
