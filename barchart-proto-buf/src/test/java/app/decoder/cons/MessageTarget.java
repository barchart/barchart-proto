package app.decoder.cons;

public interface MessageTarget {

	DataConsumer getDataConsumer();

	NewsConsumer getNewsConsumer();

}
