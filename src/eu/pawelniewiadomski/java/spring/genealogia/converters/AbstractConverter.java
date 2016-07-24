package eu.pawelniewiadomski.java.spring.genealogia.converters;

import eu.pawelniewiadomski.java.spring.genealogia.model.AbstractModel;

public interface AbstractConverter<M extends AbstractModel, O extends Object > {
	public O convert(M source);
}
