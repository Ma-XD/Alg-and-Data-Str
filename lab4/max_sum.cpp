#include <iostream>
#include <vector>
#include <cmath>


void set(std::vector<long long > &tree, std::vector<long long > &tree_max, std::vector<long long > &tree_left, std::vector<long long > &tree_right,  long long v, size_t i, size_t x, size_t lx, size_t rx) {
    if (rx - lx == 1) {
        tree[x] = v;
        tree_max[x] = v;
        tree_left[x] = v;
        tree_right[x] = v;
        return;
    }
    size_t m = (lx + rx) / 2;
    if (i < m) {
        set(tree, tree_max, tree_left, tree_right, v, i, 2 * x + 1, lx, m);
    } else {
        set(tree, tree_max, tree_left, tree_right, v, i, 2 * x + 2, m, rx);
    }
    tree[x] = tree[2 * x + 1] + tree[2 * x + 2];
    tree_max[x] = std::max(
            std::max(tree_max[2 * x + 1], tree_max[2 * x +2]),
            tree_right[2 * x + 1] + tree_left[2 * x + 2]
            );
    tree_left[x] = std::max(
            tree_left[2 * x + 1],
            tree[2 * x + 1] + tree_left[2 * x + 2]
            );
    tree_right[x] = std::max(
            tree_right[2 * x + 2],
            tree[2 * x + 2] + tree_right[2 * x + 1]
            );
}

void print(std::vector<long long > &tree) {
    for (long long i : tree) {
        std::cout << i << " ";
    }
    std::cout << "\n";
}

int main() {
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(nullptr);
    //std::cout.tie(nullptr);

    size_t n, num_op;
    std::cin >> n;
    std::cin >> num_op;
    std::vector<long long > a(n);
    for (size_t i = 0; i < n; i++) {
        std::cin >> a[i];
    }
    size_t len = 1 << (static_cast<size_t>(log(n - 1) / log(2) + 1));
    std::vector<long long > tree(len * 2 - 1, 0);
    std::vector<long long > tree_max(len * 2 - 1, 0);
    std::vector<long long > tree_left(len * 2 - 1, 0);
    std::vector<long long > tree_right(len * 2 - 1, 0);
    for (size_t i = 0; i < n; i++) {
        set(tree, tree_max, tree_left, tree_right,  a[i], i, 0, 0, len);
    }
    std::cout << (tree_max[0] < 0? 0: tree_max[0]) << std::endl;
    for (size_t i = 0; i < num_op; i++) {
        size_t index;
        long long value;
        std::cin >> index >> value;
        set(tree, tree_max, tree_left, tree_right, value, index, 0, 0, len);
        std::cout << (tree_max[0] < 0? 0: tree_max[0])  << std::endl;
    }
    return 0;
}
