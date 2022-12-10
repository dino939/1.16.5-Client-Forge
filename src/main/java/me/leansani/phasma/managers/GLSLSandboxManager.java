package me.leansani.phasma.managers;

import com.mojang.blaze3d.platform.GlStateManager;
import me.leansani.phasma.utils.FileUtil;
import org.lwjgl.opengl.GL30;


public class GLSLSandboxManager {
	public static final String VERTEX_SORUCE = FileUtil.getShaderSource("vertex.vsh");
	private final int programId;
	private final long initTime;
	
	public GLSLSandboxManager(String fragmentShaderName) {
		int programId = GlStateManager.glCreateProgram();
		try {
			int vertexShader = GlStateManager.glCreateShader(GL30.GL_VERTEX_SHADER);
			GlStateManager.glShaderSource(vertexShader, VERTEX_SORUCE);
			GlStateManager.glCompileShader(vertexShader);
			int isVertexCompiled = GlStateManager.glGetShaderi(vertexShader, GL30.GL_COMPILE_STATUS);
			
			if(isVertexCompiled == 0) {
				GlStateManager.glDeleteShader(vertexShader);
			}
			
			int fragmentShader = GlStateManager.glCreateShader(GL30.GL_FRAGMENT_SHADER);
			GlStateManager.glShaderSource(fragmentShader, FileUtil.getShaderSource(fragmentShaderName));
			GlStateManager.glCompileShader(fragmentShader);
			
			int isFragmentCompiled = GlStateManager.glGetShaderi(fragmentShader, GL30.GL_COMPILE_STATUS);
			if(isFragmentCompiled == 0) {
				GlStateManager.glDeleteShader(fragmentShader);
			}
			
			GlStateManager.glAttachShader(programId, vertexShader);
			GlStateManager.glAttachShader(programId, fragmentShader);
			GlStateManager.glLinkProgram(programId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.programId = programId;
		this.initTime = System.currentTimeMillis();
	}
	
	public void bind(double mouseX, double mouseY, double resolutionX, double resolutionY, int time) {
		GlStateManager._glUseProgram(programId);
		GL30.glUniform2f(GL30.glGetUniformLocation(programId, "resolution"), (float)resolutionX, (float)resolutionY);
		GL30.glUniform2f(GL30.glGetUniformLocation(programId, "mouse"), (float)mouseX, (float)mouseY);
		GL30.glUniform1f(GL30.glGetUniformLocation(programId, "time"), (System.currentTimeMillis() - initTime) / 1000f / time);
	}
	
	public void unbind() {GlStateManager._glUseProgram(0);}
}
