import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        String[] components = path.split("/");

        for (String dir : components) {
            if (dir.equals("") || dir.equals(".")) {
                // Ignore empty parts (from consecutive slashes) and current directory '.'
                continue;
            } else if (dir.equals("..")) {
                // Go back to parent directory if possible
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                // Valid directory or file name (e.g., "home", "...")
                stack.push(dir);
            }
        }

        // Reconstruct the simplified canonical path from bottom to top
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append("/").append(stack.pollLast());
        }

        // Return "/" if the stack was empty, otherwise return the built path
        return result.length() == 0 ? "/" : result.toString();
    }
}