package org.marketcetera.core.metrics;

import java.io.IOException;
import java.io.PrintStream;

/**
 * A factory that abstracts the location to which the instrumentation metrics
 * are summarized when {@link ThreadedMetric#summarizeResults(PrintStreamFactory)} 
 * is invoked.
 *
 * @author anshul@marketcetera.com
 * @version $Id: PrintStreamFactory.java 16063 2012-01-31 18:21:55Z colin $
 * @since 2.0.0
 */
interface PrintStreamFactory {
    /**
     * Gets the print stream to write metrics for the thread identified by
     * the supplied name. Subclasses may choose to create a new stream or
     * return a reference to an existing stream.
     *
     * @param inName the metrics name. The name corresponds to the
     *  name of the thread that generated these metrics.
     *
     * @return the stream to which the metrics should be summarized.
     * 
     * @throws IOException if there were errors getting the stream.
     */
    public PrintStream getStream(String inName) throws IOException;

    /**
     * This method is invoked when the system is done writing metrics summary
     * to the stream. Subclasses may choose to either close or flush the stream.
     *
     * @param inStream the stream to which metrics have been summarized.
     * 
     * @throws IOException if there were errors closing the stream.
     */
    public void done(PrintStream inStream) throws IOException;
}