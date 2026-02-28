// main.cpp
#include <GL/glew.h>
#include <GLFW/glfw3.h>
#include <cstdio>
#include <cmath>

// Vertex coordinates for a cube (8 vertices)
GLfloat vertices[] = {
    -0.5f, -0.5f, -0.5f, //0
     0.5f, -0.5f, -0.5f, //1
     0.5f,  0.5f, -0.5f, //2
    -0.5f,  0.5f, -0.5f, //3
    -0.5f, -0.5f,  0.5f, //4
     0.5f, -0.5f,  0.5f, //5
     0.5f,  0.5f,  0.5f, //6
    -0.5f,  0.5f,  0.5f  //7
};

// Indices for drawing cube faces with triangles
GLuint indices[] = {
    0,1,2, 2,3,0, // back
    4,5,6, 6,7,4, // front
    0,4,7, 7,3,0, // left
    1,5,6, 6,2,1, // right
    3,2,6, 6,7,3, // top
    0,1,5, 5,4,0  // bottom
};

void errorCallback(int error, const char* description) {
    fprintf(stderr, "GLFW Error: %s\n", description);
}

int main() {
    glfwSetErrorCallback(errorCallback);

    if (!glfwInit()) {
        fprintf(stderr, "Failed to initialize GLFW\n");
        return -1;
    }

    // Create window 800x600, OpenGL 3.3 Core Profile
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

    GLFWwindow* window = glfwCreateWindow(800, 600, "Basic Spinning Cube", NULL, NULL);
    if (!window) {
        fprintf(stderr, "Failed to create GLFW window\n");
        glfwTerminate();
        return -1;
    }

    glfwMakeContextCurrent(window);

    // Init GLEW (must be after context creation)
    glewExperimental = GL_TRUE;
    if (glewInit() != GLEW_OK) {
        fprintf(stderr, "Failed to initialize GLEW\n");
        return -1;
    }

    // Enable depth testing (important for 3D)
    glEnable(GL_DEPTH_TEST);

    // Main loop
    while (!glfwWindowShouldClose(window)) {
        // Clear color and depth buffers
        glClearColor(0.1f, 0.15f, 0.2f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        // Simple spinning effect (just a rotation)
        float time = (float)glfwGetTime();
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glRotatef(time * 50.f, 1.0f, 1.0f, 0.0f);

        // Draw the cube (immediate mode, old school style)
        glBegin(GL_TRIANGLES);
        for (int i = 0; i < 36; ++i) {
            // Color changes by vertex index for fun
            glColor3f((i % 3) / 2.0f, ((i + 1) % 3) / 2.0f, ((i + 2) % 3) / 2.0f);
            int v = indices[i];
            glVertex3fv(&vertices[v * 3]);
        }
        glEnd();

        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    glfwDestroyWindow(window);
    glfwTerminate();

    return 0;
}
