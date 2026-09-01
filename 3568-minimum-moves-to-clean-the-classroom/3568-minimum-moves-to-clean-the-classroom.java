class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = 0;
        int startC = 0;

        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                
                if (ch == 'S') {
                    startR = i;
                    startC = j;
                }
                
                if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        int targetMask = (1 << litterCount) - 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startR, startC, 0, energy, 0});
        
        int totalMasks = 1 << litterCount;
        boolean[] visited = new boolean[m * n * totalMasks * (energy + 1)];

        int startState = encode(startR, startC, 0, energy, n, totalMasks, energy);
        visited[startState] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            
            int r = current[0];
            int c = current[1];
            int mask = current[2];
            int e = current[3];
            int moves = current[4];

            if (mask == targetMask) {
                return moves;
            }

            if (e == 0) {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                if (classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                int newEnergy = e - 1;
                int newMask = mask;

                if (classroom[nr].charAt(nc) == 'L') {
                    int id = litterId[nr][nc];
                    newMask = mask | (1 << id);
                }

                if (classroom[nr].charAt(nc) == 'R') {
                    newEnergy = energy;
                }

                int state = encode(nr, nc, newMask, newEnergy, n, totalMasks, energy);

                if (!visited[state]) {
                    visited[state] = true;
                    queue.offer(new int[]{nr, nc, newMask, newEnergy, moves + 1});
                }
            }
        }

        return -1;
    }

    private int encode(int r, int c, int mask, int e, int n, int totalMasks, int maxEnergy) {
        return ((((r * n) + c) * totalMasks) + mask) * (maxEnergy + 1) + e;
    }
}