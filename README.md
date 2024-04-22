# car-park
Programa onde utilizo o recurso de interface para que meu projeto fique mais fácil de manutenir-lo. 

- A utiulização de interface em programas nos possibilita a deixar um codigo mais flexivel e facil de alterar pois aqui utilizo a injenção de dependencia, que e uma forma de iversão de controle, onde tiro a responsabilidade da Classe RentalCar de instanciar a Classe concreta "BrazilTaxService" pois quando crio a interface, ela faz o trabalho de comunicar com qualquer outra classe que for implementa-la.

- Com isso posso alterar a classe concreta a qualquer momento que a classe RentalCar não será alterada. Isso e mais para uma boa prática. 
