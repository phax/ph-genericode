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

import com.helger.cva.v10.ContextValueAssociation;
import com.helger.cva.v10.ObjectFactory;
import com.helger.jaxb.GenericJAXBMarshaller;

/**
 * This is the reader and writer for CVA 1.0 documents. This class may be
 * derived to override protected methods from {@link GenericJAXBMarshaller}.
 *
 * @author Philip Helger
 */
public class CVA10Marshaller extends GenericJAXBMarshaller <ContextValueAssociation>
{
  /**
   * Constructor
   */
  public CVA10Marshaller ()
  {
    super (ContextValueAssociation.class, CCVA.CVA_10_XSDS, new ObjectFactory ()::createContextValueAssociation);
  }
}
