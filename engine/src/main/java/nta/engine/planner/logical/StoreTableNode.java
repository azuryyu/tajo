package nta.engine.planner.logical;

import com.google.gson.annotations.Expose;

import nta.catalog.Column;
import nta.engine.json.GsonCreator;

/**
 * @author Hyunsik Choi
 * 
 */
public class StoreTableNode extends UnaryNode {
  @Expose
  private final String tableName;
  
  @Expose
  private int numPartitions;
  @Expose
  private Column [] partitionKeys;

  public StoreTableNode(String tableName) {
    super(ExprType.STORE);
    this.tableName = tableName;
  }

  public final String getTableName() {
    return this.tableName;
  }
    
  public final int getNumPartitions() {
    return this.numPartitions;
  }
  
  public final boolean hasPartitionKey() {
    return this.partitionKeys != null;
  }
  
  public final Column [] getPartitionKeys() {
    return this.partitionKeys;
  }
  
  public final void setPartitions(Column [] keys, int numPartitions) {
    this.partitionKeys = keys;
    this.numPartitions = numPartitions;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\"Store\": {\"table\": \""+tableName+"\",");
    if (partitionKeys != null)
      sb.append("\"partition keys: [");
    for (int i = 0; i < partitionKeys.length; i++) {
      sb.append(partitionKeys[i]);
      if (i < partitionKeys.length - 1)
        sb.append(",");
    }
    sb.append("\n  \"out schema\": ").append(getOutputSchema()).append(",")
    .append("\n  \"in schema\": ").append(getInputSchema())
    .append("}");
    
    return sb.toString() + "\n"
        + getSubNode().toString();
  }
  
  public String toJSON() {
    return GsonCreator.getInstance().toJson(this, LogicalNode.class);
  }
}