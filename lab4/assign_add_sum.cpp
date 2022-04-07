#include <iostream>
#include <vector>
#include <cmath>
#include <climits>

void propagate(std::vector<long long> &tree_assign,
               std::vector<long long > &tree_add,
               std::vector<long long> &tree,
               size_t x, size_t lx, size_t rx) {
    if (tree_assign[x] != -1) {
        tree_assign[2 * x + 1] = tree_assign[x];
        tree_assign[2 * x + 2] = tree_assign[x];
        tree_assign[x] = -1;
        tree[2 * x + 1] = tree[x] / 2;
        tree[2 * x + 2] = tree[x] / 2;
        tree_add[x] = 0;
    }
    tree[2 * x + 1] += tree_add[x] * (rx - lx) / 2;
    tree[2 * x + 2] += tree_add[x] * (rx - lx) / 2;
    tree_add[2 * x + 1] += tree_add[x];
    tree_add[2 * x + 2] += tree_add[x];
    tree_add[x] = 0;
}


void assign(std::vector<long long > &tree_assign,
            std::vector<long long > &tree_add,
            std::vector<long long > &tree,
            size_t l, size_t r, long long v, size_t x, size_t lx, size_t rx) {
    if (l >=  rx || lx >= r) {
        return;
    }
    if (lx >= l && rx <= r) {
        tree_assign[x] = v;
        tree[x] = v * (rx - lx);
        return;
    }
    propagate(tree_assign, tree_add,  tree, x, lx, rx);
    size_t m = (lx + rx) / 2;
    assign(tree_assign, tree_add, tree, l, r, v, 2 * x + 1, lx, m);
    assign(tree_assign, tree_add, tree, l, r, v, 2 * x + 2, m, rx);
    tree[x] = tree[2 * x + 1] + tree[2 * x + 2] ;
}

void add(std::vector<long long > &tree_assign,
         std::vector<long long > &tree_add,
         std::vector<long long > &tree,
         size_t l, size_t r, long long v, size_t x, size_t lx, size_t rx) {
    if (l >=  rx || lx >= r) {
        return;
    }
    if (lx >= l && rx <= r) {
        if (tree_assign[x] != -1) {
            tree_assign[x] += v;
        }
        tree_add[x] += v;
        tree[x] += v * (rx - lx);
        return;
    }
    propagate(tree_assign, tree_add, tree, x, lx, rx);
    size_t m = (lx + rx) / 2;
    add(tree_assign, tree_add, tree, l, r, v, 2 * x + 1, lx, m);
    add(tree_assign, tree_add, tree, l, r, v, 2 * x + 2, m, rx);
    tree[x] = tree[2 * x + 1] + tree[2 * x + 2];
}

long long sum(std::vector<long long > &tree_assign,
              std::vector<long long > &tree_add,
              std::vector<long long > &tree,
              size_t l, size_t r, size_t x, size_t lx, size_t rx) {
    if (l >=  rx || lx >= r) {
        return 0;
    }
    if (lx >= l && rx <= r) {
        return tree[x];
    }
    propagate(tree_assign, tree_add, tree, x, lx, rx);
    size_t m = (lx + rx) / 2;
    long long sum1 = sum(tree_assign, tree_add, tree, l, r, 2 * x + 1, lx, m);
    long long sum2 = sum(tree_assign, tree_add, tree, l, r, 2 * x + 2, m, rx);
    return sum1 + sum2;
}


void print(std::vector<long long > &tree_assign) {
    for (long long i : tree_assign) {
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
    size_t len = 1 << (static_cast<size_t>(log(n - 1) / log(2) + 1));
    std::vector<long long > tree_assign(len * 2 - 1, 0);
    std::vector<long long > tree_add(len * 2 - 1, 0);
    for (size_t i = 0; i < len - 1; i++) {
        tree_assign[i] = -1;
    }
    std::vector<long long > tree(len * 2 - 1, 0);

    for (size_t i = 0; i < num_op; i++) {
        int op;
        long long v;
        size_t l, r;
        std::cin >> op;
        std::cin >> l >> r;
        if (op == 1) {
            std::cin >> v;
            assign(tree_assign, tree_add, tree, l, r, v, 0, 0, len);
        } else if (op == 2) {
            std::cin >> v;
            add(tree_assign, tree_add, tree, l, r, v, 0, 0, len);
        } else {
            std::cout << sum(tree_assign, tree_add, tree, l, r, 0, 0, len) << std::endl;
        }
        //print(tree);
        //print(tree_add);
        //print(tree_assign);
    }
    return 0;

}

