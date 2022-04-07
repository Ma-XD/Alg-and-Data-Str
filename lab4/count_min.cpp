#include <iostream>
#include <vector>
#include <cmath>
#include <climits>

void set(std::vector<long long > &tree, std::vector<int> &tree_min, long long v, size_t i, size_t x, size_t lx, size_t rx) {
    if (rx - lx == 1) {
        tree[x] = v;
        tree_min[x] = 1;
        return;
    }
    size_t m = (lx + rx) / 2;
    if (i < m) {
        set(tree,tree_min, v, i, 2 * x + 1, lx, m);
    } else {
        set(tree, tree_min,  v, i, 2 * x + 2, m, rx);
    }
    if (tree[2 * x + 1] < tree[2 * x + 2]) {
        tree[x] = tree[2 * x + 1];
        tree_min[x] = tree_min[2 * x + 1];
    } else {
        tree[x] = tree[2 * x + 2];
        tree_min[x] = tree_min[2 * x + 2];
        if (tree[2 * x + 1] ==  tree[2 * x + 2]) {
            tree_min[x] += tree_min[2 * x + 1];
        }
    }
}

std::pair<long long, int> min(std::vector<long long > &tree, std::vector<int> &tree_min, size_t l, size_t r, size_t x, size_t lx, size_t rx) {
    if (l >=  rx || lx >= r) {
        return {LLONG_MAX, -1};
    }
    if (lx >= l && rx <= r) {
        return  {tree[x], tree_min[x]};
    }
    size_t m = (lx + rx) / 2;
    std::pair<long long, int> p = min(tree, tree_min, l, r, 2 * x + 1, lx, m);
    long long min1 = p.first;
    int num1 = p.second;
    p = min(tree, tree_min, l, r, 2 * x + 2, m, rx);
    long long min2 = p.first;
    int num2 = p.second;
    if (min1 < min2) {
        return {min1, num1};
    } else {
        if (min1 == min2) {
            num2 += num1;
        }
        return {min2, num2};
    }
}


int main() {
    size_t n, num_op;
    std::cin >> n;
    std::cin >> num_op;
    std::vector<long long > a(n);
    for (size_t i = 0; i < n; i++) {
        std::cin >> a[i];
    }
    size_t len = 1 << (static_cast<size_t>(log(n - 1) / log(2) + 1));
    std::vector<long long > tree(len * 2 - 1, 0);
    std::vector<int> tree_min(len * 2 - 1, 0);
    for (size_t i = 0; i < n; i++) {
        set(tree, tree_min, a[i], i, 0, 0, len);
    }
    for (size_t i = 0; i < num_op; i++) {
        int op;
        std::cin >> op;
        if (op == 1) {
            long long value;
            size_t index;
            std::cin >> index >> value;
            set(tree, tree_min, value, index, 0, 0 , len);
            //print(tree);
        } else {
            size_t l, r;
            std::cin >> l >> r;
            std:: pair<long long, int> p =  min(tree, tree_min, l, r, 0, 0, len);
            std::cout << p.first << " " << p.second << std::endl;
        }
    }
    return 0;

}
