# Iterators

[![Maven](https://img.shields.io/maven-metadata/v/https/dl.bintray.com/alexb/iterator-pack/com/alexb/iterator-pack/maven-metadata.xml)](https://bintray.com/alexb/iterator-pack/iterators/_latestVersion)

A collection of iterators for Java.

| Iterator             | Description                                                               |
| -------------------- | ------------------------------------------------------------------------- |
| CombinationsIterator | Generates k-length subsequences of elements from input collection.        |
| DaysIterator         | Generates consecutive days starting from Monday, January 1, 1900.         |
| DivisorsIterator     | Generates proper divisors of the input number.                            |
| FactorsIterator      | Generates prime factors of the input number.                              |
| FibsIterator         | Generates a sequence of Fibonacci numbers.                                |
| PartitionsIterator   | Generates all possible ways to partition elements from input collection.  |
| PermutationsIterator | Generates successive k-length permutations of elements in the collection. |
| PrimesIterator       | Generates a sequence of prime numbers.                                    |

## Setting up the dependency

Resolving artifacts:

```groovy
repositories {
    maven {
        url "https://dl.bintray.com/alexb/iterator-pack"
    }
}
```

Including dependency:

```groovy
dependencies {
    implementation 'com.alexb:iterator-pack:1.x.y'
}
```

## How to use

`java.util.Iterator` is a fundamental and versatile interface, which can be wrapped in any kind of
collection/sequence/stream. To illustrate the most basic use, let's output all combinations of
pairs of characters 'A', 'B', and 'C':

```java
CombinationsIterator<Character> it = new CombinationsIterator<>(Arrays.asList('A', 'B', 'C'), 2);
while (it.hasNext()) {
    System.out.println(it.next());
}
```

More advanced use of iterator is to wrap it in RxJava's Observable, which opens up a host of
filter/map and other stream operators. To give an example, let's output first 10 prime numbers
that end with 3:  

```java
Observable<Long> primes = Observable.generate(
    PrimesIterator::new,
    (primes, emitter) -> {
        emitter.onNext(primes.next());
    }
);
primes
    .filter(p -> p % 10 == 3)
    .take(10)
    .blockingSubscribe(System.out::println);

```

## License

```
Copyright 2020-present, Alex Baryzhikov.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
