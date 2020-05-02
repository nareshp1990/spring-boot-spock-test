package com.example.spock

import groovy.util.logging.Slf4j
import spock.lang.Specification
import spock.lang.Unroll

@Slf4j
class MyFirstSpecification extends Specification{

    // fields
    // fixture methods
    // feature methods
    // helper methods

    // There are six kinds of blocks: setup, when, then, expect, cleanup, where

    Stack stack

    def setup() {
        stack = new Stack()
        assert stack.empty
    }

    def "check pop() when stack is empty"(){
        when:
        stack.pop()
        then:
        thrown(EmptyStackException)
        stack.empty()
    }

    def "check pop() when stack is empty second way"(){
        when:
        stack.pop()
        then:
        def e = thrown(EmptyStackException)
        log.info("### e -> ",e)
        e.cause == null
    }

    def "check pop() when stack is empty third way"(){
        when:
        stack.pop()
        then:
        EmptyStackException e = thrown()
        log.info("### e -> ",e)
        e.cause == null
    }

    def "pushing an element on the stack"() {
        // blocks go here

        setup:
        def stack = new Stack()
        def elem = "push me"
        when:
        stack.push(elem)
        then:
        !stack.isEmpty()
        stack.size() == 1
        stack.peek() == elem

    }

    def "HashMap accepts null key"() {
        setup:
        def map = new HashMap()
        map.put(null, "elem")
    }

    def "HashMap accepts null key second way"() {
        setup:
        def map = new HashMap()

        when:
        map.put(null, "elem")

        then:
        notThrown(NullPointerException)
    }

    def "expect block example"(){
        expect:
        Math.max(1,2) == 2
    }

    def "cleanup block example"(){
        setup:
        def  file = new File("SampleFile.txt")

        when:
        file.createNewFile()

        then:
        file.exists() == true

        cleanup:
        file.delete()
    }

    def "where block example"(){
        expect:
        Math.max(a, b) == c

        //Although it is declared last the where block is evaluated before feature method runs.
        where:
        a << [5, 3]
        b << [1, 9]
        c << [5, 9]
    }

    //Data Driven Testing
    def "maximum of two numbers"(int a, int b, int c){
        expect:
        Math.max(a,b) == c

        where:
        a | b | c
        1 | 3 | 3
        7 | 4 | 7
        0 | 0 | 0
    }

    def "maximum of two numbers another way"() {
        expect:
        Math.max(a, b) == c

        where:
        a | b || c
        1 | 3 || 3
        7 | 4 || 7
        0 | 0 || 0
    }

    @Unroll
    def "maximum of two numbers with Unroll"() {
        expect:
        Math.max(a, b) == c

        where:
        a | b || c
        1 | 3 || 3
        7 | 4 || 7
        0 | 0 || 0
    }

    @Unroll
    def "maximum of #a and #b is #c"() {
        expect:
        Math.max(a, b) == c

        where:
        a | b || c
        1 | 3 || 3
        7 | 4 || 7
        0 | 0 || 0
    }

    /*def "events are published to all subscribers"() {
        def subscriber1 = Mock(Subscriber)
        def subscriber2 = Mock(Subscriber)
        def publisher = new Publisher()
        publisher.add(subscriber1)
        publisher.add(subscriber2)

        when:
        publisher.fire("event")

        then:
        1 * subscriber1.receive("event")
        1 * subscriber2.receive("event")
    }*/
}
