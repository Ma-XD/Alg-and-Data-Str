# Лабораторная 5. Дерево поиска

## A. Простое двоичное дерево поиска

Реализуйте просто двоичное дерево поиска.

* **Входные данные:** Входной файл содержит описание операций с деревом, их количество не превышает **100**. В каждой строке находится одна из следующих операций:
  * `insert x` — добавить в дерево ключ **x**. Если ключ **x** есть в дереве, то ничего делать не надо;
  * `delete x` — удалить из дерева ключ **x**. Если ключа **x** в дереве нет, то ничего делать не надо;
  * `exists x` — если ключ **x** есть в дереве выведите `true`, если нет `false`;
  * `next x` — выведите минимальный элемент в дереве, строго больший **x**, или `none` если такого нет;
  * `prev x` — выведите максимальный элемент в дереве, строго меньший **x**, или `none` если такого нет.

  В дерево помещаются и извлекаются только целые числа, не превышающие по модулю **10<sup>9</sup>**.

* **Выходные данные:**
Выведите последовательно результат выполнения всех операций `exists`, `next`, `prev`. Следуйте формату выходного файла из примера.

### Пример

* **Входные данные:**

      insert 2
      insert 5
      insert 3
      exists 2
      exists 4
      next 4
      prev 4
      delete 5
      next 4
      prev 4

* **Выходные данные:**

      true
      false
      5
      3
      none
      3

#### [&решение](https://github.com/Ma-XD/Alg-and-Data-Str/blob/main/Lab5/src/BinarySearchTree.java)

## B. Сбалансированное двоичное дерево поиска

Реализуйте сбалансированное двоичное дерево поиска.

* **Входные данные:**
Входной файл содержит описание операций с деревом, их количество не превышает **10<sup>5</sup>**. В каждой строке находится одна из следующих операций:
  * `insert x` — добавить в дерево ключ **x**. Если ключ **x** есть в дереве, то ничего делать не надо;
  * `delete x` — удалить из дерева ключ **x**. Если ключа **x** в дереве нет, то ничего делать не надо;
  * `exists x` — если ключ **x** есть в дереве выведите `true`, если нет `false`;
  * `next x` — выведите минимальный элемент в дереве, строго больший **x**, или `none` если такого нет;
  * `prev x` — выведите максимальный элемент в дереве, строго меньший **x**, или `none` если такого нет.

  В дерево помещаются и извлекаются только целые числа, не превышающие по модулю **10<sup>9</sup>**.

* **Выходные данные:**
Выведите последовательно результат выполнения всех операций `exists`, `next`, `prev`. Следуйте формату выходного файла из примера.

### Пример

* **Входные данные:**

      insert 2
      insert 5
      insert 3
      exists 2
      exists 4
      next 4
      prev 4
      delete 5
      next 4
      prev 4

* **Выходные данные:**

      true
      false
      5
      3
      none
      3

#### [&решение](https://github.com/Ma-XD/Alg-and-Data-Str/blob/main/Lab5/src/AVLTree.java)

## D. И снова сумма

Реализуйте структуру данных, которая поддерживает множество **S** целых чисел, с котором разрешается производить следующие операции:

* `add(i)` - добавить в множество **S** число **i** (если он там уже есть, то множество не меняется);
* `sum(l, r)` - вывести сумму всех элементов **x** из **S**, которые удовлетворяют неравенству **l ≤ x ≤ r**.

* **Входные данные:**
Исходно множество **S** пусто. Первая строка входного файла содержит **n** — количество операций **(1 ≤ n ≤ 300000)**. Следующие **n** строк содержат операции. Каждая операция имеет вид либо `+ i`, либо `? l r`. Операция `? l r` задает запрос `sum(l, r)`.

    Если операция `+ i` идет во входном файле в начале или после другой операции **«+»**, то она задает операцию. Если же она идет после запроса **«?»**, и результат этого запроса был y, то выполняется операция `add((i + y) mod 10`<sup>`9`</sup>`)`.

    Во всех запросах и операциях добавления параметры лежат в интервале от **0** до **10<sup>9</sup>**.

* **Выходные данные:**
Для каждого запроса выведите одно число — ответ на запрос.

### Пример

* **Входные данные:**

      6
      + 1
      + 3
      + 3
      ? 2 4
      + 1
      ? 2 4

* **Выходные данные:**

      3
      7

#### [&решение](https://github.com/Ma-XD/Alg-and-Data-Str/blob/main/Lab5/src/TheSumAgain.java)

## E. K-й максимум

Напишите программу, реализующую структуру данных, позволяющую добавлять и удалять элементы, а также находить **k**-й максимум.

* **Входные данные:**
Первая строка входного файла содержит натуральное число **n** — количество команд **(n ≤ 100000)**. Последующие **n** строк содержат по одной команде каждая. Команда записывается в виде двух чисел **ci** и **ki** — тип и аргумент команды соответственно **(|ki| ≤ 10<sup>9</sup>)**. Поддерживаемые команды:
  * `1`: Добавить элемент с ключом **ki**.
  * `0`: Найти и вывести **ki**-й максимум.
  * `-1`: Удалить элемент с ключом **ki**.

  Гарантируется, что в процессе работы в структуре не требуется хранить элементы с равными ключами или удалять несуществующие элементы. Также гарантируется, что при запросе **ki**-го максимума, он существует.

* **Выходные данные:**
Для каждой команды нулевого типа в выходной файл должна быть выведена строка, содержащая единственное число — **ki**-й максимум.

### Пример

* **Входные данные:**

      11
      1 5
      1 3
      1 7
      0 1
      0 2
      0 3
      -1 5
      1 10
      0 1
      0 2
      0 3

* **Выходные данные:**

      7
      5
      3
      10
      7
      3

#### [&решение](https://github.com/Ma-XD/Alg-and-Data-Str/blob/main/Lab5/src/KMax.java)

## F. Неявный ключ

Научитесь быстро делать две операции с массивом:  

* `add i x` — добавить после **i**-го элемента **x (0≤i≤n)**
* `del i` — удалить **i**-й элемент **(1 ≤ i ≤ n)**

* **Входные данные:**
На первой строке **n0** и **m (1 ≤ n0, m ≤ 10<sup>5</sup>)** — длина исходного массива и количество запросов. На второй строке **n0** целых чисел от **0** до **10<sup>9</sup> - 1** — исходный массив. Далее **m** строк, содержащие запросы. Гарантируется, что запросы корректны: например, если просят удалить **i**-й элемент, он точно есть.

* **Выходные данные:**
Выведите конечное состояние массива. На первой строке количество элементов, на второй строке сам массив.

### Пример

* **Входные данные:**

      3 4
      1 2 3
      del 3
      add 0 9
      add 3 8
      del 2

* **Выходные данные:**

      3
      9 2 8 

#### [&решение](https://github.com/Ma-XD/Alg-and-Data-Str/blob/main/Lab5/src/ImplicitKey.java)

## G. Переместить в начало

Вам дан массив **a1 = 1, a2 = 2, ..., an = n** и последовальность операций: переместить элементы с **li** по **ri** в начало массива. Например, для массива **2, 3, 6, 1, 5, 4**, после операции **(2, 4)** новый порядок будет **3, 6, 1, 2, 5, 4**. А после применения операции **(3, 4)** порядок элементов в массиве будет **1, 2, 3, 6, 5, 4**.

Выведите порядок элементов в массиве после выполнения всех операций.

* **Входные данные:**
В первой строке входного файла указаны числа **n** и **m (2 ≤ n ≤ 100000, 1 ≤ m ≤ 100000)** — число элементов в массиве и число операций. Следующие **m** строк содержат операции в виде двух целых чисел: **li** и **ri (1 ≤ li ≤ ri ≤ n)**.

* **Выходные данные:**
Выведите **n** целых чисел — порядок элементов в массиве после применения всех операций.

### Пример

* **Входные данные:**

      6 3
      2 4
      3 5
      2 2

* **Выходные данные:**

      1 4 5 2 3 6 

#### [&решение](https://github.com/Ma-XD/Alg-and-Data-Str/blob/main/Lab5/src/MoveToStart.java)

## H. Развороты

Вам дан массив **a1 = 1, a2 = 2, ..., an = n** и последовательность операций: переставить элементы с **li** по **ri** в обратном порядке. Например, для массива **1, 2, 3, 4, 5**, после операции **(2, 4)** новый порядок будет **1, 4, 3, 2, 5**. А после применения операции **(3, 5)** порядок элементов в массиве будет **1, 4, 5, 2, 3**.

Выведите порядок элементов в массиве после выполнения всех операций.

* **Входные данные:**
В первой строке входного файла указаны числа **n** и **m (2 ≤ n ≤ 100000, 1 ≤ m ≤ 100000)** — число элементов в массиве и число операций. Следующие **m** строк содержат операции в виде двух целых чисел: **li** и **ri (1 ≤ li ≤ ri ≤ n)**.

* **Выходные данные:**
Выведите **n** целых чисел — порядок элементов в массиве после применения всех операций

### Пример

* **Входные данные:**

      5 3
      2 4
      3 5
      2 2

* **Выходные данные:**

      1 4 5 2 3 

#### [&решение](https://github.com/Ma-XD/Alg-and-Data-Str/blob/main/Lab5/src/Reversals.java)
