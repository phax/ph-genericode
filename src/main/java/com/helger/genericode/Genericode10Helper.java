/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.string.StringHelper;
import com.helger.genericode.v10.Column;
import com.helger.genericode.v10.ColumnRef;
import com.helger.genericode.v10.ColumnSet;
import com.helger.genericode.v10.Data;
import com.helger.genericode.v10.Key;
import com.helger.genericode.v10.KeyColumnRef;
import com.helger.genericode.v10.LongName;
import com.helger.genericode.v10.ObjectFactory;
import com.helger.genericode.v10.Row;
import com.helger.genericode.v10.ShortName;
import com.helger.genericode.v10.SimpleValue;
import com.helger.genericode.v10.UseType;
import com.helger.genericode.v10.Value;

/**
 * Helper class for Genericode 1.0 reading
 *
 * @author Philip Helger
 */
@Immutable
public final class Genericode10Helper
{
  private static final ObjectFactory s_aFactory = new ObjectFactory ();

  private Genericode10Helper ()
  {}

  /**
   * Get the ID of the passed column element.
   *
   * @param aColumnElement
   *        The column element to use. Must be either a {@link ColumnRef} or a
   *        {@link Column}.
   * @return The ID of the object
   */
  @Nonnull
  public static String getColumnElementID (@Nonnull final Object aColumnElement)
  {
    if (aColumnElement instanceof ColumnRef)
      return ((ColumnRef) aColumnElement).getId ();
    if (aColumnElement instanceof Column)
      return ((Column) aColumnElement).getId ();
    if (aColumnElement instanceof Key)
    {
      final List <KeyColumnRef> aKeyColumnRefs = ((Key) aColumnElement).getColumnRef ();
      final KeyColumnRef aKeyColumnRef = CollectionHelper.getFirstElement (aKeyColumnRefs);
      if (aKeyColumnRef == null)
        throw new IllegalArgumentException ("Key contains not KeyColumnRef!!");
      final Object aRef = aKeyColumnRef.getRef ();
      if (aRef instanceof Column)
        return ((Column) aRef).getId ();
      throw new IllegalArgumentException ("Unsupported referenced object: " +
                                          aRef +
                                          " - " +
                                          ClassHelper.getSafeClassName (aRef));
    }
    throw new IllegalArgumentException ("Illegal column element: " +
                                        aColumnElement +
                                        " - " +
                                        ClassHelper.getSafeClassName (aColumnElement));
  }

  /**
   * Get the value of a column identified by an ID within a specified row. This
   * method only handles simple values.
   *
   * @param aRow
   *        The row to scan. May not be <code>null</code>.
   * @param sColumnID
   *        The ID of the column to search. May not be <code>null</code>.
   * @return <code>null</code> if no such column is contained
   */
  @Nullable
  public static String getRowValue (@Nonnull final Row aRow, @Nonnull final String sColumnID)
  {
    for (final Value aValue : aRow.getValue ())
    {
      final String sID = getColumnElementID (aValue.getColumnRef ());
      if (sID.equals (sColumnID))
      {
        final SimpleValue aSimpleValue = aValue.getSimpleValue ();
        return aSimpleValue != null ? aSimpleValue.getValue () : null;
      }
    }
    return null;
  }

  /**
   * Get all contained columns
   *
   * @param aColumnSet
   *        The column set to scan. May not be <code>null</code>.
   * @return A non-<code>null</code> list of all columns. Never
   *         <code>null</code> but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static List <Column> getAllColumns (@Nonnull final ColumnSet aColumnSet)
  {
    final List <Column> ret = new ArrayList <Column> ();
    for (final Object o : aColumnSet.getColumnChoice ())
      if (o instanceof Column)
        ret.add ((Column) o);
    return ret;
  }

  /**
   * Get the IDs of all contained columns
   *
   * @param aColumnSet
   *        The column set to scan. May not be <code>null</code>.
   * @return A non-<code>null</code> list of all column IDs. Never
   *         <code>null</code> but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static List <String> getAllColumnIDs (@Nonnull final ColumnSet aColumnSet)
  {
    final List <String> ret = new ArrayList <String> ();
    for (final Column aColumn : getAllColumns (aColumnSet))
      ret.add (aColumn.getId ());
    return ret;
  }

  /**
   * Get the column with the specified ID.
   *
   * @param aColumnSet
   *        The column set to scan. May not be <code>null</code>.
   * @param sID
   *        The ID to search. May be <code>null</code>.
   * @return <code>null</code> if no such column exists.
   */
  @Nullable
  public static Column getColumnOfID (@Nonnull final ColumnSet aColumnSet, @Nullable final String sID)
  {
    if (sID != null)
      for (final Column aColumn : getAllColumns (aColumnSet))
        if (aColumn.getId ().equals (sID))
          return aColumn;
    return null;
  }

  /**
   * Get all contained keys
   *
   * @param aColumnSet
   *        The column set to scan. May not be <code>null</code>.
   * @return A non-<code>null</code> list of all keys. Never <code>null</code>
   *         but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static List <Key> getAllKeys (@Nonnull final ColumnSet aColumnSet)
  {
    final List <Key> ret = new ArrayList <Key> ();
    for (final Object o : aColumnSet.getKeyChoice ())
      if (o instanceof Key)
        ret.add ((Key) o);
    return ret;
  }

  /**
   * Get the IDs of all contained keys
   *
   * @param aColumnSet
   *        The column set to scan. May not be <code>null</code>.
   * @return A non-<code>null</code> list of all key IDs. Never
   *         <code>null</code> but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static List <String> getAllKeyIDs (@Nonnull final ColumnSet aColumnSet)
  {
    final List <String> ret = new ArrayList <String> ();
    for (final Key aKey : getAllKeys (aColumnSet))
      ret.add (aKey.getId ());
    return ret;
  }

  /**
   * Get the key with the specified ID.
   *
   * @param aColumnSet
   *        The column set to scan. May not be <code>null</code>.
   * @param sID
   *        The ID to search. May be <code>null</code>.
   * @return <code>null</code> if no such key exists.
   */
  @Nullable
  public static Key getKeyOfID (@Nonnull final ColumnSet aColumnSet, @Nullable final String sID)
  {
    if (sID != null)
      for (final Key aKey : getAllKeys (aColumnSet))
        if (aKey.getId ().equals (sID))
          return aKey;
    return null;
  }

  /**
   * Check if the passed column ID is a key column in the specified column set
   *
   * @param aColumnSet
   *        The column set to scan. May not be <code>null</code>.
   * @param sColumnID
   *        The column ID to search. May be <code>null</code>.
   * @return <code>true</code> if the passed column ID is a key column
   */
  public static boolean isKeyColumn (@Nonnull final ColumnSet aColumnSet, @Nullable final String sColumnID)
  {
    if (sColumnID != null)
      for (final Key aKey : getAllKeys (aColumnSet))
        for (final KeyColumnRef aColumnRef : aKey.getColumnRef ())
          if (aColumnRef.getRef () instanceof Column)
            if (((Column) aColumnRef.getRef ()).getId ().equals (sColumnID))
              return true;
    return false;
  }

  /**
   * Create a {@link ShortName} object
   *
   * @param sValue
   *        The value to assign
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static ShortName createShortName (@Nullable final String sValue)
  {
    final ShortName aShortName = s_aFactory.createShortName ();
    aShortName.setValue (sValue);
    return aShortName;
  }

  /**
   * Create a {@link LongName} object
   *
   * @param sValue
   *        The value to assign
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static LongName createLongName (@Nullable final String sValue)
  {
    final LongName aLongName = s_aFactory.createLongName ();
    aLongName.setValue (sValue);
    return aLongName;
  }

  /**
   * Create a {@link SimpleValue} object
   *
   * @param sValue
   *        The value to assign
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static SimpleValue createSimpleValue (@Nullable final String sValue)
  {
    final SimpleValue aSimpleValue = s_aFactory.createSimpleValue ();
    aSimpleValue.setValue (sValue);
    return aSimpleValue;
  }

  /**
   * Create a {@link KeyColumnRef} object
   *
   * @param aColumn
   *        The column to reference
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static KeyColumnRef createKeyColumnRef (@Nullable final Column aColumn)
  {
    final KeyColumnRef aColumnRef = s_aFactory.createKeyColumnRef ();
    // Important: reference the object itself and not just the ID!!!
    aColumnRef.setRef (aColumn);
    return aColumnRef;
  }

  /**
   * Create a new column to be added to a column set
   *
   * @param sColumnID
   *        The ID of the column
   * @param eUseType
   *        The usage type (optional or required)
   * @param sShortName
   *        The short name of the column
   * @param sLongName
   *        The long name of the column
   * @param sDataType
   *        The data type to use
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static Column createColumn (@Nonnull @Nonempty final String sColumnID,
                                     @Nonnull final UseType eUseType,
                                     @Nonnull @Nonempty final String sShortName,
                                     @Nullable final String sLongName,
                                     @Nonnull @Nonempty final String sDataType)
  {
    if (StringHelper.hasNoText (sColumnID))
      throw new IllegalArgumentException ("No column ID provided!");
    if (eUseType == null)
      throw new NullPointerException ("useType");
    if (StringHelper.hasNoText (sShortName))
      throw new IllegalArgumentException ("No short name provided!");
    if (StringHelper.hasNoText (sDataType))
      throw new IllegalArgumentException ("No data type provided!");

    final Column aColumn = s_aFactory.createColumn ();
    aColumn.setId (sColumnID);
    aColumn.setUse (eUseType);
    aColumn.setShortName (createShortName (sShortName));
    if (StringHelper.hasText (sLongName))
      aColumn.getLongName ().add (createLongName (sLongName));
    final Data aData = s_aFactory.createData ();
    aData.setType (sDataType);
    aColumn.setData (aData);
    return aColumn;
  }

  /**
   * Create a new key to be added to a column set
   *
   * @param sColumnID
   *        The ID of the column
   * @param sShortName
   *        The short name of the column
   * @param sLongName
   *        The long name of the column
   * @param aColumn
   *        The referenced column. May not be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static Key createKey (@Nonnull @Nonempty final String sColumnID,
                               @Nonnull @Nonempty final String sShortName,
                               @Nullable final String sLongName,
                               @Nonnull final Column aColumn)
  {
    if (StringHelper.hasNoText (sColumnID))
      throw new IllegalArgumentException ("No column ID provided!");
    if (StringHelper.hasNoText (sShortName))
      throw new IllegalArgumentException ("No short name provided!");
    if (aColumn == null)
      throw new NullPointerException ("column");

    final Key aKey = s_aFactory.createKey ();
    aKey.setId (sColumnID);
    aKey.setShortName (createShortName (sShortName));
    if (StringHelper.hasText (sLongName))
      aKey.getLongName ().add (createLongName (sLongName));
    aKey.getColumnRef ().add (createKeyColumnRef (aColumn));
    return aKey;
  }
}
