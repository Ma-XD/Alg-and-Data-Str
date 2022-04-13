#include <iostream>
#include <vector>
#include <cmath>

void propagate(std::vector<int > &tree_cnt,
               std::vector<int > &tree_sum,
               std::vector<int > &tree_left,
               std::vector<int > &tree_right,
               int x, int lx, int rx) {
    int c = (tree_cnt[x] == 0)? 0: 1;
    if (tree_sum[x] == (rx - lx) * c) {
        tree_cnt[2 * x + 1] = c;
        tree_sum[2 * x + 1] = (rx - lx) / 2 * c;
        tree_left[2 * x + 1] = c;
        tree_right[2 * x + 1] = c;
        tree_cnt[2 * x + 2] = c;
        tree_sum[2 * x + 2] = (rx - lx) / 2 * c;
        tree_left[2 * x + 2] = c;
        tree_right[2 * x + 2] = c;
    }
}

void set(std::vector<int > &tree_cnt,
            std::vector<int > &tree_sum,
            std::vector<int > &tree_left,
            std::vector<int > &tree_right,
            int l, int r, int c, int x, int lx, int rx) {
    if (l >=  rx || lx >= r) {
        return;
    }
    if (lx >= l && rx <= r) {
        tree_cnt[x] = c;
        tree_sum[x] = (rx - lx) * c;
        tree_left[x] = c;
        tree_right[x] = c;
        return;
    }
    propagate(tree_cnt, tree_sum, tree_left, tree_right, x, lx, rx);
    int m = (lx + rx) / 2;
    set(tree_cnt, tree_sum, tree_left, tree_right, l, r, c, 2 * x + 1, lx, m);
    set(tree_cnt, tree_sum, tree_left, tree_right, l, r, c, 2 * x + 2, m, rx);
    tree_cnt[x] = tree_cnt[2 * x + 1] + tree_cnt[2 * x + 2];
    if (tree_right[2 * x + 1] == tree_left[2 * x + 2] &&
        tree_right[2 * x + 1] == 1 && tree_left[2 * x + 2] == 1) {
        tree_cnt[x]--;
    }
    tree_sum[x] = tree_sum[2 * x + 1] + tree_sum[2 * x + 2];
    tree_left[x] = tree_left[2 * x + 1];
    tree_right[x] = tree_right[2 * x + 2];
}

void print(std::vector<int > &tree) {
    for (int i : tree) {
        std::cout << i << " ";
    }
    std::cout << "\n";
}

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);

    const int MAX_CORD = 1000000;
    const int num_leaves = 1 << (static_cast<int>(log(MAX_CORD - 1) / log(2) + 1));
    int n;
    std::cin >> n;
    std::vector<int> tree_cnt(num_leaves * 2 - 1, 0);
    std::vector<int> tree_sum(num_leaves * 2 - 1, 0);
    std::vector<int > tree_left(num_leaves * 2 - 1, 0);
    std::vector<int > tree_right(num_leaves * 2 - 1, 0);

    for (int i = 0; i < n; ++i) {
        char colour;
        int c, l, d;
        std::cin >> colour >> l >> d;
        c = (colour == 'W')? 0: 1;
        set(tree_cnt, tree_sum, tree_left, tree_right, l + MAX_CORD / 2, l + d + MAX_CORD / 2, c, 0, 0, num_leaves);
        //print(tree_sum);
        std::cout << tree_cnt[0] << " " << tree_sum[0] << std::endl;
    }

    return 0;
}
