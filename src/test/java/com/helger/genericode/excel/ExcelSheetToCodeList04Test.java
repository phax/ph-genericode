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
package com.helger.genericode.excel;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.w3c.dom.Document;

import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.genericode.Genericode04CodeListMarshaller;
import com.helger.genericode.v04.CodeListDocument;
import com.helger.genericode.v04.UseType;
import com.helger.poi.excel.EExcelVersion;

/**
 * Text class for class {@link ExcelSheetToCodeList04}.
 *
 * @author Philip Helger
 */
public final class ExcelSheetToCodeList04Test
{
  @Test
  public void testReadExcel () throws URISyntaxException
  {
    // Where is the Excel?
    final IReadableResource aXls = new ClassPathResource ("excel/Simple1.xls");
    assertTrue (aXls.exists ());

    // Interpret as Excel
    final Workbook aWB = EExcelVersion.XLS.readWorkbook (aXls.getInputStream ());
    assertNotNull (aWB);

    // Check whether all required sheets are present
    final Sheet aSheet = aWB.getSheetAt (0);
    assertNotNull (aSheet);

    final ExcelReadOptions <UseType> aReadOptions = new ExcelReadOptions <UseType> ().setLinesToSkip (1)
                                                                                     .setLineIndexShortName (0);
    aReadOptions.addColumn (0, "id", UseType.REQUIRED, "string", true);
    aReadOptions.addColumn (1, "name", UseType.REQUIRED, "string", false);
    final CodeListDocument aCodeList = ExcelSheetToCodeList04.convertToSimpleCodeList (aSheet,
                                                                                       aReadOptions,
                                                                                       "ExampleList",
                                                                                       "1.0",
                                                                                       new URI ("urn:www.helger.com:names:example"),
                                                                                       new URI ("urn:www.helger.com:names:example-1.0"),
                                                                                       null);
    final Document aDoc = new Genericode04CodeListMarshaller ().getAsDocument (aCodeList);
    assertNotNull (aDoc);
    final CodeListDocument aCLDoc = new Genericode04CodeListMarshaller ().read (aDoc);
    assertNotNull (aCLDoc);
  }
}
