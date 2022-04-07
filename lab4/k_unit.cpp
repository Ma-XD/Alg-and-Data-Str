#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

void set(vector<int> &tree, size_t i, size_t x, size_t lx, size_t rx) {
    if (rx - lx == 1) {
        tree[x] = (tree[x] + 1) % 2;
        return;
    }
    size_t m = (lx + rx) / 2;
    if (i < m) {
        set(tree,  i, 2 * x + 1, lx, m);
    } else {
        set(tree, i, 2 * x + 2, m, rx);
    }

    tree[x] = tree[2 * x + 1] + tree[2 * x + 2];
}

size_t k_one(vector<int> &tree, int k, size_t x, size_t lx, size_t rx) {
    if (rx - lx == 1) {
        return x - tree.size() / 2;
    }
    size_t m = (lx + rx) / 2;
    if (k + 1 <= tree[2 * x + 1]) {
        k_one(tree, k, 2 * x + 1, lx, m);
    } else {
        k_one(tree, k - tree[2 * x + 1], 2 * x + 2, m, rx);
    }
}

void print(vector<int> &tree) {
    for (int i : tree) {
        cout << i << " ";
    }
    cout << "\n";
}

int main() {
    size_t n, num_op;
    cin >> n;
    cin >> num_op;
    vector<int> a(n);
    for (size_t i = 0; i < n; i++) {
        cin >> a[i];
    }
    size_t len = 1 << (static_cast<size_t>(log(n - 1) / log(2) + 1));
    vector<int> tree(len * 2 - 1, 0);
    for (size_t i = 0; i < n; i++) {
        if (a[i] == 1) {
            set(tree, i, 0, 0, len);
        }
    }
    //print(tree);

    for (size_t i = 0; i < num_op; i++) {
        int op;
        cin >> op;
        if (op == 1) {
            size_t index;
            cin >> index;
            set(tree, index, 0, 0 , len);
            //print(tree);
        } else {
            size_t k;
            cin >> k;
            cout << k_one(tree, k, 0, 0, len) << endl;
        }
    }
    return 0;
}
