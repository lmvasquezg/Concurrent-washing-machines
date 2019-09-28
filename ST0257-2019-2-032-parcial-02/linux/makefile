all: bin bin/lavdual

CXXFLAGS=-std=c++11

bin/lavdual: src/main.o src/lavadora.o src/sincronizador.o src/agentevoid.o src/gencarga.o src/gencargaimpl.o
	$(CXX) -o $@ $^ -pthread

src/main.o: src/main.cpp src/lavadora.h src/sincronizador.h src/agentevoid.h src/gencarga.h src/gencargaimpl.h

src/lavadora.o: src/lavadora.cpp src/lavadora.h src/lavadoraid.h src/sincronizador.h src/gencarga.h

src/gencarga.o: src/gencarga.cpp src/gencarga.h

src/gencargaimpl.o: src/gencarga.cpp src/gencargaimpl.h src/gencarga.h

src/agentevoid.o: src/agentevoid.cpp src/agentevoid.h src/sincronizador.h src/gencarga.h src/lavadoraid.h

src/sincronizador.o: src/sincronizador.cpp src/sincronizador.h

bin:
	mkdir bin

clean:
	rm -f *.o src/*.o src/*.*~ src/*~
	rm -f bin/lavdual
	rmdir bin
