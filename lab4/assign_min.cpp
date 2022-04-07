#include <iostream>
#include <vector>
#include <cmath>
#include <climits>

void propagate(std::vector<long long> &tree, std::vector<long long> &tree_min,  size_t x) {
    if (tree[x] != -1) {
        tree[2 * x + 1] = tree[x];
        tree[2 * x + 2] = tree[x];
        tree_min[2 * x + 1] = tree_min[x];
        tree_min[2 * x + 2] = tree_min[x];
        tree[x] = -1;
    }
}

void assign(std::vector<long long > &tree, std::vector<long long > &tree_min, size_t l, size_t r, long long v, size_t x, size_t lx, size_t rx) {
    if (l >=  rx || lx >= r) {
        return;
    }
    if (lx >= l && rx <= r) {
        tree[x] = v;
        tree_min[x] = v;
        return;
    }
    propagate(tree, tree_min, x);
    size_t m = (lx + rx) / 2;
    assign(tree, tree_min, l, r, v, 2 * x + 1, lx, m);
    assign(tree, tree_min, l, r, v, 2 * x + 2, m, rx);
    tree_min[x] = std::min(tree_min[2 * x + 1], tree_min[2 * x + 2]);
}

long long min(std::vector<long long > &tree, std::vector<long long > &tree_min, size_t l, size_t r, size_t x, size_t lx, size_t rx) {
    if (l >=  rx || lx >= r) {
        return LLONG_MAX;
    }
    if (lx >= l && rx <= r) {
        return tree_min[x];
    }
    propagate(tree, tree_min, x);
    size_t m = (lx + rx) / 2;
    long long min1 = min(tree, tree_min, l, r, 2 * x + 1, lx, m);
    long long min2 = min(tree, tree_min, l, r, 2 * x + 2, m, rx);
    return std::min(min1, min2);
}


void print(std::vector<long long > &tree) {
    for (long long i : tree) {
        std::cout << i << " ";
    }
    std::cout << "\n";
}

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);

    size_t n, num_op;
    std::cin >> n;
    std::cin >> num_op;
    size_t len = 1 << (static_cast<size_t>(log(n - 1) / log(2) + 1));
    std::vector<long long > tree(len * 2 - 1, 0);
    for (size_t i = 0; i < len - 1; i++) {
        tree[i] = -1;
    }
    std::vector<long long > tree_min(len * 2 - 1, 0);

    for (size_t i = 0; i < num_op; i++) {
        int op;
        size_t l, r;
        std::cin >> op;
        std::cin >> l >> r;
        if (op == 1) {
            long long v;
            std::cin >> v;
            assign(tree, tree_min, l, r, v, 0, 0, len);
        } else {
            std::cout << min(tree, tree_min, l, r, 0, 0, len) << std::endl;
        }
    }
    return 0;

}

