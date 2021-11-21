all: run

clean:
	rm -f out/EuclidAlgorithm.jar out/MainEuclidParcs.jar

build:
	@javac -cp out/parcs.jar src/com/euclid/MainEuclidParcs.java src/com/euclid/model/NumberPair.java src/com/euclid/model/TaskWithData.java
	@cd src && jar cf ../out/MainEuclidParcs.jar com/euclid/MainEuclidParcs.class com/euclid/model/NumberPair.class com/euclid/model/TaskWithData.class
	@rm -f src/com/euclid/MainEuclidParcs.class src/com/euclid/model/NumberPair.class src/com/euclid/model/TaskWithData.class

	@javac -cp out/parcs.jar src/com/euclid/algoritm/EuclidAlgorithm.java src/com/euclid/model/NumberPair.java
	@cd src && jar cf ../out/EuclidAlgorithm.jar com/euclid/algoritm/EuclidAlgorithm.class com/euclid/model/NumberPair.java
	@rm -f src/com/euclid/algoritm/EuclidAlgorithm.class  src/com/euclid/model/NumberPair.class

run:
	@cd out && java -cp 'parcs.jar:MainEuclidParcs.jar' com.euclid.MainEuclidParcs
