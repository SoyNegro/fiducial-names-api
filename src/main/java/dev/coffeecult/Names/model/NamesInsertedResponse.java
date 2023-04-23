package dev.coffeecult.Names.model;

import java.util.HashSet;

/*
* Given "Names" should be uniques, a "Set" is a proper
* representation of the expected list of values expected to be persisted
* */
public record NamesInsertedResponse(HashSet<String> names) {
}
