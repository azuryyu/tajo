/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.tajo.engine.function.builtin;

import org.apache.tajo.catalog.CatalogUtil;
import org.apache.tajo.catalog.Column;
import org.apache.tajo.common.TajoDataTypes;
import org.apache.tajo.common.TajoDataTypes.Type;
import org.apache.tajo.engine.function.annotation.Description;
import org.apache.tajo.engine.function.annotation.ParamTypes;

@Description(
    functionName = "lead",
    description = "the nth next row value from current row",
    example = "> SELECT lead(column, n) OVER ();",
    returnType = Type.FLOAT4,
    paramTypes = {@ParamTypes(paramTypes = {Type.FLOAT4}), @ParamTypes(paramTypes = {Type.FLOAT4, Type.INT4}), @ParamTypes(paramTypes = {Type.FLOAT4, Type.INT4, Type.FLOAT4})}
)
public class LeadFloat extends Lead {

  public LeadFloat() {
    super(new Column[] {
        new Column("col", Type.FLOAT4),
        new Column("num", Type.INT4),
        new Column("default", Type.FLOAT4)
    });
  }

  @Override
  public TajoDataTypes.DataType getPartialResultType() {
    return CatalogUtil.newSimpleDataType(Type.FLOAT4);
  }
}
