package nta.engine.planner.physical;

import static org.junit.Assert.assertEquals;
import nta.catalog.Schema;
import nta.catalog.proto.CatalogProtos.DataType;
import nta.datum.Datum;
import nta.datum.DatumFactory;
import nta.engine.parser.QueryBlock.SortSpec;
import nta.storage.Tuple;
import nta.storage.VTuple;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Hyunsik Choi
 */
public class TestTupleComparator {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public final void testCompare() {
    Schema schema = new Schema();
    schema.addColumn("col1", DataType.INT);
    schema.addColumn("col2", DataType.INT);
    schema.addColumn("col3", DataType.INT);
    schema.addColumn("col4", DataType.INT);
    schema.addColumn("col5", DataType.STRING);
    
    Tuple tuple1 = new VTuple(5);
    Tuple tuple2 = new VTuple(5);

    tuple1.put(
        new Datum[] {
        DatumFactory.createInt(9), 
        DatumFactory.createInt(3),
        DatumFactory.createInt(33), 
        DatumFactory.createInt(4),
        DatumFactory.createString("abc")});
    tuple2.put(
        new Datum[] {
        DatumFactory.createInt(1), 
        DatumFactory.createInt(25),
        DatumFactory.createInt(109),
        DatumFactory.createInt(4),
        DatumFactory.createString("abd")});

    SortSpec sortKey1 = new SortSpec(schema.getColumn("col4"), true, false);
    SortSpec sortKey2 = new SortSpec(schema.getColumn("col5"), true, false);

    TupleComparator tc = new TupleComparator(schema, 
        new SortSpec[] {sortKey1, sortKey2}, null);
    assertEquals(-1, tc.compare(tuple1, tuple2));
    assertEquals(1, tc.compare(tuple2, tuple1));
  }
}
