package auth_environment.view.tabs;

import java.util.ResourceBundle;

import auth_environment.FrontEndCreator;
import auth_environment.view.UnitPicker;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class UnitTab extends Tab{
	
	private static final String LABEL_PACKAGE = "auth_environment/properties/creation_tab_labels";
	private static final ResourceBundle myLabelsBundle = ResourceBundle.getBundle(LABEL_PACKAGE);
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/creation_tab_dimensions";
	private static final ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private int index;
	private FrontEndCreator myCreator;
	private BorderPane myPane;

	public UnitTab(String name) {
		super(name);
		this.myPane = new BorderPane();
		this.setContent(myPane);
	}
	
	public void setUp(){
		refresh();
		index = 1;
		this.setClosable(false);
		myCreator = new FrontEndCreator();
	    this.setClosable(false);
	    
	    myPane.setRight(setUpUnitPicker().getRoot());
	    
		BorderPane newBorderPane = new BorderPane();
	    myPane.setLeft(setUpTitledPane(newBorderPane));
	    setUpAnimation(newBorderPane);
	    
        GridPane newTableInfo = setUpGridPane(myLabelsBundle.getString("newTablePaneDim"));
		newBorderPane.setLeft(newTableInfo);
		
        Text propertiesTitle = new Text("Properties");
        propertiesTitle.setFont(new Font(20));
        newTableInfo.add(propertiesTitle, 0, 0);
        addFields(newTableInfo, myCreator);
		
		GridPane bottomInfo = setUpPane(myLabelsBundle.getString("bottomInfoDim"));
 		newBorderPane.setBottom(bottomInfo);
 		Button ok = myCreator.createButton(bottomInfo, myLabelsBundle.getString("okText"), 0, 2);
		ok.setOnAction(e -> createNewElement());
	}
	
	public abstract void refresh();
	
	public abstract void setUpAnimation(BorderPane bp);
	
	public abstract void addFields(GridPane gp, FrontEndCreator creator);
	
	public void reset(){
		myPane.getChildren().clear();
	}
	
	public FrontEndCreator getCreator(){
		return myCreator;
	}
	
	public void iterateIndex(){
		index++;
	}
	
	public int getIndex(){
		return index;
	}
	
	public void setIndex(int val){
		index = val;
	}
	
	public ResourceBundle getLabelsBundle(){
		return myLabelsBundle;
	}
	
	public ResourceBundle getDimensionsBundle(){
		return myDimensionsBundle;
	}

	public abstract void createNewElement();
	
	public abstract UnitPicker setUpUnitPicker();
	
	public GridPane setUpGridPane(String s){
        GridPane gridPane = setUpPane(s);
        gridPane.getRowConstraints().addAll(new RowConstraints(Double.parseDouble(myDimensionsBundle.getString("rowConstraintSize"))));
        gridPane.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("newTableWidth")), Double.parseDouble(myDimensionsBundle.getString("newTableHeight")));
		
		return gridPane;
	}
	
	public TitledPane setUpTitledPane(BorderPane bp){
		ScrollPane sp = new ScrollPane();
		TitledPane titledPane= new TitledPane();
		titledPane.setText(myLabelsBundle.getString("newLabel"));
		titledPane.setContent(sp);
		titledPane.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("newPaneWidth")), Double.parseDouble(myDimensionsBundle.getString("newPaneHeight")));
		titledPane.setCollapsible(false);
		sp.setContent(bp);
		return titledPane;
	}
	
	public GridPane setUpPane(String s){
		GridPane gridPane = new GridPane();
        gridPane.getColumnConstraints().addAll(new ColumnConstraints(Double.parseDouble(myDimensionsBundle.getString(s))),new ColumnConstraints(Double.parseDouble(myDimensionsBundle.getString(s+0))),new ColumnConstraints(Double.parseDouble(myDimensionsBundle.getString(s+1))),new ColumnConstraints(Double.parseDouble(myDimensionsBundle.getString(s+2))));
		return gridPane;
	}
	
	
}
