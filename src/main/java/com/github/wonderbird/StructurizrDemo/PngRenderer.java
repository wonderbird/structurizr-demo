package com.github.wonderbird.StructurizrDemo;

import com.structurizr.io.WorkspaceWriterException;

import java.io.IOException;
import java.nio.file.Path;

public interface PngRenderer
{
   void renderTo(Path aFilePath) throws WorkspaceWriterException, IOException, InterruptedException;
}
