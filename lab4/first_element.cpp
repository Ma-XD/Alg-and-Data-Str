#include <iostream>
#include <vector>
#include <cmath>
#include <set>

void set(std::vector<long long> &tree, long long v, size_t i, size_t x, size_t lx, size_t rx) {
    if (rx - lx == 1) {
        tree[x] = v;
        return;
    }
    size_t m = (lx + rx) / 2;
    if (i < m) {
        set(tree, v, i, 2 * x + 1, lx, m);
    } else {
        set(tree, v, i, 2 * x + 2, m, rx);
    }
    tree[x] = std::max(tree[2 * x + 1], tree[2 * x + 2]);
}


size_t get_min_index2(std::vector<long long> &tree, long long v, size_t l, size_t x, size_t lx, size_t rx) {
    if (tree[x] < v || l >= rx) {
        return -1;
    }
    if (rx - lx == 1) {
        return  x - tree.size() / 2;
    }
    size_t m = (rx + lx) / 2;
    size_t res = get_min_index2(tree, v, l, 2 * x + 1, lx, m);
    if (res > 100000) {
        return get_min_index2(tree, v, l, 2 * x + 2, m, rx );
    }
    return res;
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
    std::vector<long long > a(n);
    for (size_t i = 0; i < n; i++) {
        std::cin >> a[i];
    }
    size_t len = 1 << (static_cast<size_t>(log(n - 1) / log(2) + 1));
    std::vector<long long> tree(len * 2 - 1);
    for (size_t i = 0; i < n; i++) {
        set(tree, a[i], i, 0, 0, len);
    }

    for (size_t i = 0; i < num_op; i++) {
        int op;
        std::cin >> op;
        if (op == 1) {
            size_t index;
            long long value;
            std::cin >> index >> value;
            set(tree, value, index, 0, 0 , len);
            //print(tree);
        } else {
            size_t l;
            long long x;
            std::cin >> x >> l;
            size_t j = get_min_index2(tree, x, l, 0, 0, len);
            if (j > 100000) {
                std::cout << -1 << std::endl;
            } else {
                std::cout << j << std::endl;
            }
        }
    }
    return 0;
}

