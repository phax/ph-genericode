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

import java.io.Serializable;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.ToStringGenerator;

/**
 * This class represents a single column definition when converting an Excel
 * sheet into a code list.
 *
 * @author Philip Helger
 * @param <USE_TYPE>
 *        The UseType of either GeneriCode 0.4 or 1.0
 */
@Immutable
public class ExcelReadColumn <USE_TYPE extends Serializable> implements Serializable
{
  private final int m_nIndex;
  private final String m_sColumnID;
  private final USE_TYPE m_eUseType;
  private final String m_sDataType;
  private final boolean m_bKeyColumn;

  public ExcelReadColumn (@Nonnegative final int nIndex,
                          @Nonnull @Nonempty final String sColumnID,
                          @Nonnull final USE_TYPE eUseType,
                          @Nonnull @Nonempty final String sDataType,
                          final boolean bKeyColumn)
  {
    ValueEnforcer.isGE0 (nIndex, "Index");
    ValueEnforcer.notEmpty (sColumnID, "ColumnID");
    ValueEnforcer.notNull (eUseType, "UseType");
    // if (bKeyColumn && eUseType == UseType.OPTIONAL)
    // throw new IllegalArgumentException
    // ("Optional columns cannot be key columns!");
    ValueEnforcer.notEmpty (sDataType, "DataType");
    m_nIndex = nIndex;
    m_sColumnID = sColumnID;
    m_eUseType = eUseType;
    m_sDataType = sDataType;
    m_bKeyColumn = bKeyColumn;
  }

  /**
   * @return The 0-based index of this column.
   */
  @Nonnegative
  public int getIndex ()
  {
    return m_nIndex;
  }

  /**
   * @return The ID of this column to be used in the Genericode file.
   */
  @Nonnull
  @Nonempty
  public String getColumnID ()
  {
    return m_sColumnID;
  }

  /**
   * @return optional or required?
   */
  @Nonnull
  public USE_TYPE getUseType ()
  {
    return m_eUseType;
  }

  /**
   * @return The data type for this column.
   */
  @Nonnull
  @Nonempty
  public String getDataType ()
  {
    return m_sDataType;
  }

  /**
   * @return <code>true</code> if this is a key column, <code>false</code>
   *         otherwise. Only required columns can be key columns.
   */
  public boolean isKeyColumn ()
  {
    return m_bKeyColumn;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("index", m_nIndex)
                                       .append ("columnID", m_sColumnID)
                                       .append ("use", m_eUseType)
                                       .append ("dataType", m_sDataType)
                                       .append ("keyColumn", m_bKeyColumn)
                                       .getToString ();
  }
}
