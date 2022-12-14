package com.lp.first.framework.lucene.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;

import java.io.IOException;

public class LuceneIndex {

    private Directory directory;

    private IndexWriter indexWriter;

    private IndexReader indexReader;

    private Analyzer analyzer;

    public LuceneIndex(Directory directory, IndexWriter indexWriter, IndexReader indexReader, Analyzer analyzer) {
        this.directory = directory;
        this.indexWriter = indexWriter;
        this.indexReader = indexReader;
        this.analyzer = analyzer;
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    public IndexWriter getIndexWriter() {
        return indexWriter;
    }

    public void setIndexWriter(IndexWriter indexWriter) {
        this.indexWriter = indexWriter;
    }

    public IndexReader getIndexReader() {
        return indexReader;
    }

    public void setIndexReader(IndexReader indexReader) {
        this.indexReader = indexReader;
    }

    public Analyzer getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    public void close() {
        try {
            this.indexWriter.flush();
            this.indexWriter.close();
            this.indexReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
