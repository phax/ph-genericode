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
package com.helger.genericode;

import com.helger.genericode.v04.CodeListSetDocument;
import com.helger.genericode.v04.ObjectFactory;
import com.helger.jaxb.GenericJAXBMarshaller;

/**
 * This is the reader and writer for Genericode 0.4 code list sets. This class
 * may be derived to override protected methods from
 * {@link GenericJAXBMarshaller}.
 *
 * @author Philip Helger
 */
public class Genericode04CodeListSetMarshaller extends GenericJAXBMarshaller <CodeListSetDocument>
{
  /**
   * Constructor
   */
  public Genericode04CodeListSetMarshaller ()
  {
    super (CodeListSetDocument.class, CGenericode.GENERICODE_04_XSDS, new ObjectFactory ()::createCodeListSet);
  }
}
