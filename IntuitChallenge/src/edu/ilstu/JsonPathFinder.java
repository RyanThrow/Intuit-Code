/**
 * 
 */
package edu.ilstu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;


/**
 * @author Ryan Throw
 *
 */
class Node{
	
	private List<Node> children = new ArrayList<Node>();
	private Node parent;
	private String name, data;
	
	public Node(Node parent, String name, String data){
		this.parent = parent;
		this.name = name;
		this.data = data;
		//System.out.print(name);
	}
	
	public void addChild(Node child){
		children.add(child);
	}
	
	public List<Node> getChildren(){
		return children;
	}
	
	public Node getParent(){
		return parent;
	}
	
	public String getName(){
		return name;
	}
	
	public String getData(){
		return data;
	}
}

public class JsonPathFinder {
	
	//root node
	private Node root = null;
	
	//open file and read all data into one continuous string
	public void parseJsonFile(String fileName){
		//open file
		Scanner inputStream = null;
		try{
			inputStream = new Scanner(new File(fileName));
		}
		catch(FileNotFoundException e){
			System.out.println("Error, file not found");
			System.exit(0);
		}
		
		//build string used for parsing
		StringBuilder sb = new StringBuilder();
		while(inputStream.hasNextLine()){
			sb.append(inputStream.nextLine());
		}
		String jsonString = sb.toString();	
		
		parseJsonString(jsonString);
	}
	
	//parse the json based string
	private void parseJsonString(String jsonString){
		//parse through the json file
		JsonObject jo = new JsonParser().parse(jsonString).getAsJsonObject();
		JsonElement je = jo.get("itemList");
		
		//create root node
		root = new Node(null, "itemList", null);
		
		jo = je.getAsJsonObject();
		je = jo.get("items");
		JsonArray ja = je.getAsJsonArray();
		
		
		for(int i = 0; i < ja.size(); i++){
			//create node
			Node itemNode = new Node(root, "items[" + i + "]", null);
			root.addChild(itemNode);
			
			je = ja.get(i);
			
			//get names from id
			jo = je.getAsJsonObject();
			je = jo.get("id");
			if(je != null){
				itemNode.addChild(new Node(itemNode, "id", je.toString().substring(1, je.toString().length() - 1)));
			}
			
			//get names from label
			je = jo.get("label");
			if(je != null){
				itemNode.addChild(new Node(itemNode, "label", je.toString().substring(1, je.toString().length() - 1)));
			}
			
			//get subitems
			je = jo.get("subItems");
			if(je != null){
				//test if the fist character in the elements toString is
				//a [ bracket, if so then the object must be an array.
				if(je.toString().charAt(0) == '['){
					JsonArray ja2 = je.getAsJsonArray();
					for(int j = 0; j < ja2.size(); j++){
						//create node
						Node subItemNode = new Node(itemNode, "subItems[" + j + "]", null);
						itemNode.addChild(subItemNode);
						
						je = ja2.get(j);
						
						//get names from id
						jo = je.getAsJsonObject();
						je = jo.get("id");
						if(je != null){
							subItemNode.addChild(new Node(subItemNode, "id", je.toString().substring(1, je.toString().length() - 1)));
						}
						
						//get names from label
						je = jo.get("label");
						if(je != null){
							subItemNode.addChild(new Node(subItemNode, "label", je.toString().substring(1, je.toString().length() - 1)));
						}
					}
				}
				//then there is only one subitem and it is not stored in
				//an array
				else{
					//create node
					Node subItemNode = new Node(itemNode, "subItems", null);
					itemNode.addChild(subItemNode);
					
					jo = je.getAsJsonObject();
					
					//get names from id
					je = jo.get("id");
					if(je != null){
						subItemNode.addChild(new Node(subItemNode, "id", je.toString().substring(1, je.toString().length() - 1)));
					}
					
					//get names from label
					je = jo.get("label");
					if(je != null){
						subItemNode.addChild(new Node(subItemNode, "label", je.toString().substring(1, je.toString().length() - 1)));
					}
				}
			}
		}
	}
	
	public void findNodeByData(Node root, String data){
		//stop the recursion if the root data is equal to
		//the data the user is looking for
		if(data.equals(root.getData())){
			findAndPrintPath(root, data);
		}
		//if the data is not the same then check if the current node has
		//any children. If so then traverse through all child nodes
		else if(root.getChildren().size() > 0){
			for(int i = 0; i < root.getChildren().size(); i++){
				findNodeByData(root.getChildren().get(i), data);
			}
		}
	}
	
	private void findAndPrintPath(Node currentNode, String data){
		List<String> nodeNames = new ArrayList<String>();
		String path = "";
		
		//add nodes to list until root node is added
		while(currentNode != null){
			nodeNames.add(currentNode.getName());
			currentNode = currentNode.getParent();
		}
		
		//add nodeNames to the path in reverse order
		StringBuilder sb = new StringBuilder();
		for(int i = nodeNames.size() - 1; i >= 0; i--){
			sb.append("/" + nodeNames.get(i).toString());
		}
		path = sb.toString();
		
		System.out.println(data + " Can be found through the path: " + path);
	}
	
	public Node getRoot(){
		return root;
	}
}
