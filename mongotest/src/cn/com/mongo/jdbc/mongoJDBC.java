package cn.com.mongo.jdbc;



import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;


public class mongoJDBC {
public static void main(String[] args) {
	try{
        
	    MongoClient mongoClient=new MongoClient("127.0.0.1" , 27017);
	    //获取集合
	            MongoDatabase mongoDatabase=mongoClient.getDatabase("Student");
	            MongoCollection<Document> collection=mongoDatabase.getCollection("student");
	    System.out.println("数据库连接成功");
	  insert(mongoDatabase,collection);
	    findIterable( mongoDatabase, collection);
	    updata(collection);
	   // delete(collection);
	    //collection.deleteOne(Filters.eq("likes",200));
	    //删除所有符合条件的文档

	}
	    catch (Exception e){
	        System.out.println("书库链接失败");
	        }
	    }

	//插入数据
	public static  int insert(MongoDatabase mongoDatebase,MongoCollection<Document> collection){
	    
	    System.out.println("集合选择成功！");
	    
	    Document document=new Document("title","mongoDB");    
	    document.append("description", "database");    
	        document.append("likes", 100);
	        document.append("by", "fly");
	        List<Document> documents =new ArrayList<Document>();
	        documents.add(document);
	     collection.insertMany(documents);
	     System.out.println("插入成功！");
	     return 1;
	    
	}
	//检索文档
	//获取迭代器findIterable<document>
	//获取游标 mongoCursor<Document>
	//通过游标便利检索出文档集和

	public static  int findIterable(MongoDatabase mongoDatabase,MongoCollection<Document> collection){
	    FindIterable<Document>findIterable=collection.find();
	    MongoCursor<Document>mongoCursor=findIterable.iterator();
	    while(mongoCursor.hasNext()){
	        System.out.println(mongoCursor.next());
	        
	    }
	    System.out.println("检索完毕1");
	    
	 return 1;    
	}
	/*更新文档
	 * updataMany();
	 */
	public static  void updata(MongoCollection<Document> collection){
	    collection.updateMany(Filters.eq("likes", 100), new Document("$set",new Document("likes",200)));
	    //检索结果
	    FindIterable<Document> findIterable=collection.find();
	    MongoCursor<Document> mongocursor=findIterable.iterator();
	    while(mongocursor.hasNext()){
	        System.out.println(mongocursor.next());
	        
	        
	    }
	    System.out.println("检索完毕2");

	    //删除remove（）
	    
	}
	public static  void delete(MongoCollection<Document> collection){
	    //删除第一个文档
	    
	    //collection.deleteOne(Filters.eq("likes",200));
	    //删除所有符合条件的文档
	    collection.deleteMany(Filters.eq("likes",200));
	    FindIterable<Document> findIterable=collection.find();
	    MongoCursor<Document> mongocursor=findIterable.iterator();
	    while(mongocursor.hasNext()){
	        System.out.println(mongocursor.next());

	        
	    }
	    
	      System.out.println("删除成功！");
	}

	
}
