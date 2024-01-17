## DarvinWorld_Podmokly_Zielinski
Mateusz Podmokły\
Filip Zieliński
## PROJEKT PROGRAMOWANIE OBIEKTOWE 23/24 INFORMATYKA 2 ROK
Diagram klas:\
![diagramphoto](https://github.com/mpodmokly/DarvinWorld_Podmokly_Zielinski/blob/d7053c8574955b7263e7bd4d6db8b7e8d654460f/NOTIDEALDIAGRAM.PNG)
## UWAGI:

### DO ZROBIENIA:

- Implementacja dżungli i poprawne generowanie roślin.
  (Póki co jest robione zupełnie losowo z równym prawdopodobieństwem wszędzie.)

- Poprawna implementacja wariantu genomu.
  (Metoda `nextGene()` w `GenesSpecial` i metoda `createGene()` w `ReproductionMechanismSpecial`.)

- Poprawna implementacja wariantu mapy.
  (Metoda `sideWallHandler()` i metoda `topBottomHandler()` w klasie `MovingMechanismSpecial`.)

- Implementacja statystyk.

- Poprawne zrobienie metody `run()` w `Simulation`.

- Implementacja UI.

- (Ewentualnie) Przy mechanizmie znajdowania potomków konkretnego zwierzaka, można zamiast dla każdego zwierzaka osobny `HashSet`, zrobić graf skierowany (drzewo genealogiczne), co może być optymalniejsze.

- (Ewentualnie) Można zupełnie wyrzucić `Plant` jako klasę, a `plants` zamiast `HashMapy` to zwykły `HashSet` przechowujący pozycję rośliny.

- Poprawić rzeczy związane z generowaniem zwierzaków i rozmnażaniem (patrz niżej).

### WAŻNE RZECZY:

Metoda `getGene()` pojawia się dwa razy w dwóch lekko różnych kontekstach:

1. Jeden raz jako metoda klasy `Animal`, zwraca obiekt typu `Genes`.
   
2. Drugi raz jako metoda klasy `Genes`, zwraca obiekt typu `List<MapDirection>` czyli konkretną listę genów.
   
Obecnie zwierzaki przy generowaniu symulacji nie mogą pojawić się na tej samej pozycji. Obecnie jeżeli na jednym polu znajduje się wiele zwierzaków, tylko dwa z nich (najsilniejsze) mogą się rozmnażać.
