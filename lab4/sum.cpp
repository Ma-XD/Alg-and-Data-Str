#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

void set(vector<long long > &tree, long long v, size_t i, size_t x, size_t lx, size_t rx) {
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
    tree[x] = tree[2 * x + 1] + tree[2 * x + 2];
}

long long sum(vector<long long > &tree, size_t l, size_t r, size_t x, size_t lx, size_t rx) {
    if (l >=  rx || lx >= r) {
        return 0;
    }
    if (lx >= l && rx <= r) {
        return  tree[x];
    }
    size_t m = (lx + rx) / 2;
    return sum(tree, l, r, 2 * x + 1, lx, m) + sum(tree, l, r, 2 * x + 2, m, rx);
}

void print(vector<long long > &tree) {
    for (long long i : tree) {
        cout << i << " ";
    }
    cout << "\n";
}

int main() {
    size_t n, num_op;
    cin >> n;
    cin >> num_op;
    vector<long long > a(n);
    for (size_t i = 0; i < n; i++) {
        cin >> a[i];
    }
    size_t len = 1 << (static_cast<size_t>(log(n - 1) / log(2) + 1));
    vector<long long > tree(len * 2 - 1, 0);
    for (size_t i = 0; i < n; i++) {
        set(tree, a[i], i, 0, 0, len);
    }
    //print(tree);
    for (size_t i = 0; i < num_op; i++) {
        int op;
        cin >> op;
        if (op == 1) {
            long long value;
            size_t index;
            cin >> index >> value;
            set(tree, value, index, 0, 0 , len);
            //print(tree);
        } else {
            size_t l, r;
            cin >> l >> r;
            cout << sum(tree, l, r, 0, 0, len) << endl;
        }
    }
    return 0;
}

/**
 *              19
 *       14             5
 *   9       5       5       0
 * 5   4   2   3    5  0    0   0
 * */