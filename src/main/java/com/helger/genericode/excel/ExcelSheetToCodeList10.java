/**
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
package com.helger.genericode.excel;

import java.net.URI;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.poi.ss.usermodel.Sheet;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.string.StringHelper;
import com.helger.genericode.Genericode10Helper;
import com.helger.genericode.v10.Annotation;
import com.helger.genericode.v10.AnyOtherContent;
import com.helger.genericode.v10.CodeListDocument;
import com.helger.genericode.v10.Column;
import com.helger.genericode.v10.ColumnSet;
import com.helger.genericode.v10.Identification;
import com.helger.genericode.v10.Key;
import com.helger.genericode.v10.ObjectFactory;
import com.helger.genericode.v10.Row;
import com.helger.genericode.v10.SimpleCodeList;
import com.helger.genericode.v10.UseType;
import com.helger.genericode.v10.Value;
import com.helger.poi.excel.ExcelReadHelper;

/**
 * A utility class to convert a simple Excel sheet into a code list v1.0
 * consisting of simple values. Please note that merged cells are currently not
 * supported!
 *
 * @author Philip Helger
 */
@Immutable
public final class ExcelSheetToCodeList10
{
  public static final String ANNOTATION_NS = "urn:www.helger.com:schemas:genericode-ext";
  public static final QName QNAME_ANNOTATION = new QName (ANNOTATION_NS, "info");

  private ExcelSheetToCodeList10 ()
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
    ValueEnforcer.notNull (aExcelSheet, "ExcelSheet");
    ValueEnforcer.notNull (aReadOptions, "ReadOptions");

    final ObjectFactory aFactory = new ObjectFactory ();
    final CodeListDocument ret = aFactory.createCodeListDocument ();

    // create annotation
    final Annotation aAnnotation = aFactory.createAnnotation ();
    final AnyOtherContent aContent = aFactory.createAnyOtherContent ();
    aContent.addAny (new JAXBElement <> (QNAME_ANNOTATION, String.class, null, "Automatically created by ph-genericode. Do NOT edit."));
    aAnnotation.setAppInfo (aContent);
    ret.setAnnotation (aAnnotation);

    // create identification
    final Identification aIdentification = aFactory.createIdentification ();
    aIdentification.setShortName (Genericode10Helper.createShortName (sCodeListName));
    aIdentification.setVersion (sCodeListVersion);
    aIdentification.setCanonicalUri (aCanonicalUri.toString ());
    aIdentification.setCanonicalVersionUri (aCanonicalVersionUri.toString ());
    if (aLocationURI != null)
      aIdentification.addLocationUri (aLocationURI.toString ());
    ret.setIdentification (aIdentification);

    // create columns
    final ICommonsList <ExcelReadColumn <UseType>> aExcelColumns = aReadOptions.getAllColumns ();
    final ColumnSet aColumnSet = aFactory.createColumnSet ();
    for (final ExcelReadColumn <UseType> aExcelColumn : aExcelColumns)
    {
      // Read short name (required)
      final String sShortName = aExcelSheet.getRow (aReadOptions.getLineIndexShortName ())
                                           .getCell (aExcelColumn.getIndex ())
                                           .getStringCellValue ();

      // Read long name (optional)
      String sLongName = null;
      if (aReadOptions.getLineIndexLongName () >= 0)
        sLongName = aExcelSheet.getRow (aReadOptions.getLineIndexLongName ()).getCell (aExcelColumn.getIndex ()).getStringCellValue ();

      // Create Genericode column set
      final Column aColumn = Genericode10Helper.createColumn (aExcelColumn.getColumnID (),
                                                              aExcelColumn.getUseType (),
                                                              sShortName,
                                                              sLongName,
                                                              aExcelColumn.getDataType ());

      // add column
      aColumnSet.addColumnChoice (aColumn);

      if (aExcelColumn.isKeyColumn ())
      {
        // Create key definition
        final Key aKey = Genericode10Helper.createKey (aExcelColumn.getColumnID () + "Key", sShortName, sLongName, aColumn);

        // Add key
        aColumnSet.addKeyChoice (aKey);
      }
    }
    ret.setColumnSet (aColumnSet);

    // Read items
    final SimpleCodeList aSimpleCodeList = aFactory.createSimpleCodeList ();

    // Determine the row where reading should start
    int nRowIndex = aReadOptions.getLinesToSkip ();
    while (true)
    {
      // Read a single excel row
      final org.apache.poi.ss.usermodel.Row aExcelRow = aExcelSheet.getRow (nRowIndex++);
      if (aExcelRow == null)
        break;

      // Create Genericode row
      final Row aRow = aFactory.createRow ();
      for (final ExcelReadColumn <UseType> aExcelColumn : aExcelColumns)
      {
        final String sValue = ExcelReadHelper.getCellValueString (aExcelRow.getCell (aExcelColumn.getIndex ()));
        if (StringHelper.hasText (sValue) || aExcelColumn.getUseType () == UseType.REQUIRED)
        {
          // Create a single value in the current row
          final Value aValue = aFactory.createValue ();
          aValue.setColumnRef (Genericode10Helper.getColumnOfID (aColumnSet, aExcelColumn.getColumnID ()));
          aValue.setSimpleValue (Genericode10Helper.createSimpleValue (sValue));
          aRow.addValue (aValue);
        }
      }
      aSimpleCodeList.addRow (aRow);
    }
    ret.setSimpleCodeList (aSimpleCodeList);

    return ret;
  }
}
