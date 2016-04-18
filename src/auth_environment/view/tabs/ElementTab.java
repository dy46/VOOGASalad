package auth_environment.view.tabs;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ElementTab extends Tab{
	 
	public ElementTab(String name){
		super(name);
		init();
	}
	
	private void init(){
		BorderPane myPane = new BorderPane();
		TitledPane newPane = new TitledPane();
		TitledPane editPane = new TitledPane();
		ScrollPane newScrollPane = new ScrollPane();
		ScrollPane editScrollPane = new ScrollPane();
		BorderPane newBorderPane = new BorderPane();
		GridPane newAnimationInfo = new GridPane();					//*****	
		TitledPane newTitleInfoPane = new TitledPane();
		FlowPane editInfo = new FlowPane();							//*****	
		myPane.setLeft(newPane);
		myPane.setRight(editPane);
		
		newPane.setText("New");
		newPane.setContent(newScrollPane);
		newPane.setPrefSize(600.0, 800.0);
		newPane.setCollapsible(false);
		newScrollPane.setContent(newBorderPane);
		newBorderPane.setTop(newAnimationInfo);
		newScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		
		Button animationButton = new Button("ANIMATION");
		animationButton.setOnAction( e -> System.out.println("ANIMATION"));
		animationButton.setPrefSize(400.0,70.0);

		newAnimationInfo.getColumnConstraints().addAll(new ColumnConstraints(200), new ColumnConstraints(200), new ColumnConstraints(200));
		newAnimationInfo.getRowConstraints().addAll(new RowConstraints(70));
		newAnimationInfo.add(animationButton, 1, 0); //col, row
		
//		newTitleInfoPane.setText("Properties");
//		newTitleInfoPane.setCollapsible(false);
//		newTitleInfoPane.setPrefSize(600, 200);
        GridPane newTableInfo = new GridPane();
        newTableInfo.getColumnConstraints().addAll(new ColumnConstraints(100),new ColumnConstraints(200),new ColumnConstraints(200),new ColumnConstraints(100) );
        newTableInfo.getRowConstraints().addAll(new RowConstraints(20));
        newTableInfo.setPrefSize(600, 200);
		newBorderPane.setLeft(newTableInfo);
        
        
        Text propertiesTitle = new Text("Properties");
        propertiesTitle.setFont(new Font(20));
        newTableInfo.add(propertiesTitle, 0, 0);
        
        int index = 1;
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		String name = "Name";
		newTableInfo.add(new Text("Name: "), 1, index);
		TextField myTextField = new TextField();
		newTableInfo.add(myTextField, 2, index);
		index++;
		
		newTableInfo.getRowConstraints().add(new RowConstraints(30));
		String attack = "Attack: ";
		newTableInfo.add(new Text(attack), 1, index);
		myTextField = new TextField();
		newTableInfo.add(myTextField, 2, index);
		index++;
		
		
        
//        TableView table = new TableView();
//        
//        TableColumn nameCol = new TableColumn("Name");
//        TableColumn valueCol = new TableColumn("Value");
//        table.getColumns().addAll(nameCol, valueCol);
//        
//        newTableInfo.add(table, 1, 1);
//		

//		ObservableList<Element> data = FXCollections.observableArrayList(
//			    new Element("Name", ""),
//			    new Element("Attack", ""),
//			    new Element("Damage", ""),
//			    new Element("Emma", ""),
//			    new Element("Michael", "")
//			);
//		
//
//		nameCol.setCellValueFactory(new PropertyValueFactory<Element,String>("Name"));
//		valueCol.setCellValueFactory(new PropertyValueFactory<Element,String>("Value"));
//		table.setEditable(true);
//		
//        valueCol.setOnEditCommit(
//                new EventHandler<CellEditEvent<Element, String>>() {
//                    public void handle(CellEditEvent<Element, String> t) {
//                        ((Element) t.getTableView().getItems().get(
//                            t.getTablePosition().getRow())
//                            ).setValue(t.getNewValue());
//                    }
//                }
//            );
//		table.setItems(data);
				
	//	newTable.setItems("idk how to use this");
		
		editPane.setPrefSize(200.0, 800.0);
		editScrollPane.setContent(editInfo);
		editScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		//editScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		//editScrollPane.setPrefSize(200.0,800.0);
		editScrollPane.setFitToWidth(true);
		
		editPane.setText("Edit");
		editPane.setContent(editScrollPane);
		editPane.setCollapsible(false);
		editInfo.getChildren().addAll(new Button("YAAAS"),new Button("YESESS"),new Button("GO ME"));
		
		this.setContent(myPane);
	}
	

	
}
