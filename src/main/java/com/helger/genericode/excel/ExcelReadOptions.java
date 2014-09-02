/**
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

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.ContainerHelper;

/**
 * This class contains the options that are used to read the Excel file.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class ExcelReadOptions <USE_TYPE>
{
  /** Default lines to skip */
  public static final int DEFAULT_LINES_TO_SKIP = 0;
  public static final int DEFAULT_LINEINDEX_SHORTNAME = 0;
  public static final int DEFAULT_LINEINDEX_LONGNAME = CGlobal.ILLEGAL_UINT;

  private int m_nLinesToSkip = DEFAULT_LINES_TO_SKIP;
  private int m_nLineIndexShortName = DEFAULT_LINEINDEX_SHORTNAME;
  private int m_nLineIndexLongName = DEFAULT_LINEINDEX_LONGNAME;
  private final Map <Integer, ExcelReadColumn <USE_TYPE>> m_aColumns = new TreeMap <Integer, ExcelReadColumn <USE_TYPE>> ();

  /**
   * Constructor
   */
  public ExcelReadOptions ()
  {}

  /**
   * Set the number of lines to skip before the header row starts
   * 
   * @param nLinesToSkip
   *        Must be &ge; 0.
   * @return this
   */
  @Nonnull
  public ExcelReadOptions <USE_TYPE> setLinesToSkip (@Nonnegative final int nLinesToSkip)
  {
    ValueEnforcer.isGE0 (nLinesToSkip, "LinesToSkip");

    m_nLinesToSkip = nLinesToSkip;
    return this;
  }

  /**
   * @return The number of lines to skip before the header row starts. Default
   *         is {@value #DEFAULT_LINES_TO_SKIP}.
   */
  @Nonnegative
  public int getLinesToSkip ()
  {
    return m_nLinesToSkip;
  }

  @Nonnull
  public ExcelReadOptions <USE_TYPE> setLineIndexShortName (@Nonnegative final int nLineIndexShortName)
  {
    ValueEnforcer.isGE0 (nLineIndexShortName, "LineIndexShortName");

    m_nLineIndexShortName = nLineIndexShortName;
    return this;
  }

  @Nonnegative
  public int getLineIndexShortName ()
  {
    return m_nLineIndexShortName;
  }

  @Nonnull
  public ExcelReadOptions <USE_TYPE> setLineIndexLongName (final int nLineIndexLongName)
  {
    m_nLineIndexLongName = nLineIndexLongName;
    return this;
  }

  /**
   * @return The line index, where the long names reside. If this value is &lt;
   *         0 than no long name is used.
   */
  public int getLineIndexLongName ()
  {
    return m_nLineIndexLongName;
  }

  /**
   * Add a single column definition.
   *
   * @param nIndex
   *        The 0-based index of the column in Excel.
   * @param sColumnID
   *        The ID of the column in Genericode.
   * @param eUseType
   *        Optional or required?
   * @param sDataType
   *        The XSD data type to be used in Genericode. Use "string" if you're
   *        unsure.
   * @param bKeyColumn
   *        <code>true</code> if this is a key column, <code>false</code>
   *        otherwise. Only required columns can be key columns.
   * @return this
   */
  @Nonnull
  public ExcelReadOptions <USE_TYPE> addColumn (@Nonnegative final int nIndex,
                                                @Nonnull @Nonempty final String sColumnID,
                                                @Nonnull final USE_TYPE eUseType,
                                                @Nonnull @Nonempty final String sDataType,
                                                final boolean bKeyColumn)
  {
    ValueEnforcer.isGE0 (nIndex, "Index");

    final Integer aIndex = Integer.valueOf (nIndex);
    if (m_aColumns.containsKey (aIndex))
      throw new IllegalArgumentException ("The column at index " + nIndex + " is already mapped!");
    m_aColumns.put (aIndex, new ExcelReadColumn <USE_TYPE> (nIndex, sColumnID, eUseType, sDataType, bKeyColumn));
    return this;
  }

  /**
   * @return A list of all defined columns, sorted ascending by index.
   */
  @Nonnull
  @ReturnsMutableCopy
  public List <ExcelReadColumn <USE_TYPE>> getAllColumns ()
  {
    // Create a copy. Values are sorted ascending because of the TreeMap usage
    return ContainerHelper.newList (m_aColumns.values ());
  }
}
