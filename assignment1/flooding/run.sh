source ../teachnet.path

java -jar $tn_path --cp . --config myconfig.txt --compile

javac -cp $tn_path/teachnet.jar Flooding.java
