#include <iostream>
#include <vector>
#include <cmath>

/**
 *  Матрица 2х2 - одномерный массив
 *   {a0, a1,
 *    a2, a3}
 * */

int r;

std::vector<int> matrix_2x2_mul(const std::vector<int> &M1, const std::vector<int> &M2) {
    return std::vector<int> {
            (M1[0] * M2[0] + M1[1] * M2[2]) % r, (M1[0] * M2[1] + M1[1] * M2[3]) % r,
            (M1[2] * M2[0] + M1[3] * M2[2]) % r, (M1[2] * M2[1] + M1[3] * M2[3]) % r
    };
}

void build(std::vector<std::vector<int>> &tree, const std::vector<std::vector<int>> &matrices, int x, int lx, int rx) {
    if (rx - lx == 1) {
        if (matrices.size() > lx) {
            tree[x] = matrices[lx];
        }
        return;
    }
    int m = (lx + rx) / 2;
    build(tree, matrices, 2 * x + 1, lx, m);
    build(tree, matrices, 2 * x + 2, m, rx);
    tree[x] = matrix_2x2_mul(tree[2 * x + 1], tree[2 * x + 2]);
}

std::vector<int> mul(const std::vector<std::vector<int>> &tree, int left, int right, int x, int lx, int rx) {
    if (left >=  rx || lx >= right) {
        return std::vector<int> {1, 0,
                                 0, 1};
    }
    if (lx >= left && rx <= right) {
        return  tree[x];
    }
    int m = (lx + rx) / 2;
    return matrix_2x2_mul(
            mul(tree, left, right, 2 * x + 1, lx, m),
            mul(tree, left, right, 2 * x + 2, m, rx)
            );
}

void print_matrix(const std::vector<int> &M) {
    std::cout << M[0] << " " << M[1] << "\n" << M[2] << " " << M[3] << "\n" << std::endl;
}

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);

    int n, m;
    std::cin >> r >> n >> m;
    std::vector<std::vector<int>> matrices (n, std::vector<int> (4));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 4; j++) {
            std::cin >> matrices[i][j];
        }
    }
    int len = 1 << (static_cast<int>(log(n - 1) / log(2) + 1));
    std::vector<std::vector<int>> tree (2 * len - 1, std::vector<int> {1, 0, 0, 1});
    build(tree, matrices, 0, 0, len);
    /*
    for (int i = 0; i < tree.size(); i++) {
        std::cout << i << std::endl;
        print_matrix(tree[i]);
    }
    */
    for (int i = 0; i < m; i++) {
        int left, right;
        std::cin >> left >> right;
        print_matrix(mul(tree, left - 1, right, 0, 0, len));
    }
    return 0;
}
