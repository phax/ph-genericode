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
package com.helger.genericode.excel;

import java.net.URI;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import org.apache.poi.ss.usermodel.Sheet;

import com.helger.genericode.v10.CodeListDocument;
import com.helger.genericode.v10.UseType;

/**
 * A utility class to convert a simple Excel sheet into a code list consisting
 * of simple values. Please note that merged cells are currently not supported!
 * 
 * @author Philip Helger
 */
@NotThreadSafe
@Deprecated
public final class ExcelSheetToCodeList
{
  private ExcelSheetToCodeList ()
  {}

  @Nonnull
  public static CodeListDocument convertToSimpleCodeList (@Nonnull final Sheet aExcelSheet,
                                                          @Nonnull final ExcelReadOptions <UseType> aReadOptions,
                                                          @Nonnull final String sCodeListName,
                                                          @Nonnull final String sCodeListVersion,
                                                          @Nonnull final URI aCanonicalUri,
                                                          @Nonnull final URI aCanonicalVersionUri,
                                                          @Nullable final URI aLocationURI)
  {
    return ExcelSheetToCodeList10.convertToSimpleCodeList (aExcelSheet,
                                                           aReadOptions,
                                                           sCodeListName,
                                                           sCodeListVersion,
                                                           aCanonicalUri,
                                                           aCanonicalVersionUri,
                                                           aLocationURI);
  }
}
