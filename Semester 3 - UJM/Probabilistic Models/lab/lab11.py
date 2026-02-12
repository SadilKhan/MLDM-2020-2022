# Need:
# Installation via pip
#   pip install numpy pandas scikit-learn matplotlib POT torch
# or use uv

# Run:
# You can run interactively as "cells"
# or with
# python lab11.py
# or in headless mode (saving figures to out/fig-xxx.png) with
# HEADLESS=1 python lab11.py
# or with uv
# HEADLESS=1 uv run lab11.py

# %%
import numpy as np
import matplotlib.pyplot as plt
import os
from functools import cache

# %% Python tools

def d2o(d, typename='new'): # dict to object
    top = type(typename, (object,), {})()
    for i, j in d.items(): setattr(top, i, j)
    return top

HEADLESS = os.getenv('HEADLESS', '0') in ('1', 'true', 'True', 'TRUE')
def pltshow(o_format='out/fig-{i:03d}.png'):
    if not HEADLESS:
        plt.show()
    else:
        if not hasattr(pltshow, 'cnt'): # global counter
            pltshow.cnt = 0
        pltshow.cnt += 1
        o = o_format.format_map(dict(i=pltshow.cnt))
        plt.savefig(o)
        plt.close()
        print(f'[saved figure {o}')

# %%

print(np.arange(2) % 1)

# %%

@cache
def get_mnist():
    from tensorflow.keras.datasets import mnist
    (X_train, y_train), (X_test, y_test) = mnist.load_data()
    X = X_train.reshape(-1, 28*28).astype("float32") / 255.0
    y = y_train.astype(int)
    return X, y

def get_dataset(name, Ntrain, Ntest):
    if ':' in name:
        name, *post_processes = name.split(':')
        Xtrain, Xtest = get_dataset(name, Ntrain, Ntest)
        for p in post_processes:
            if p == 'squeeze':
                Xtrain = Xtrain / (1 + 3 * (np.arange(Xtrain.shape[1]) % 2))
                Xtest = Xtest / (1 + 3 * (np.arange(Xtrain.shape[1]) % 2))
            elif p == 'rotate':
                theta = np.pi / 4
                R = np.array([[np.cos(theta), -np.sin(theta)], [np.sin(theta), np.cos(theta)]])
                Xtrain = Xtrain @ R.T
                Xtest = Xtest @ R.T
            else:
                raise ValueError(f'Unknown post-process {p}')
        return Xtrain, Xtest
    if name == 'normal2d':
        return (
            np.random.normal(0, 1, (Ntrain, 2)),
            np.random.normal(0, 1, (Ntest, 2)),
        )
    if name == 'uniform2d':
        return (
            np.random.uniform(-1, 1, (Ntrain, 2)),
            np.random.uniform(-1, 1, (Ntest, 2)),
        )
    if name == 'gmm2d3comp':
        def sample_gmm(N):
            n1 = N // 3
            n2 = N // 3
            n3 = N - n1 - n2
            mu1 = np.array([0, 0])
            mu2 = np.array([5, 5])
            mu3 = np.array([-5, 5])
            sigma1 = np.array([[1, 0], [0, 1]])
            sigma2 = np.array([[1, 0.5], [0.5, 1]])
            sigma3 = np.array([[1, -0.5], [-0.5, 1]])
            X1 = np.random.multivariate_normal(mu1, sigma1, n1)
            X2 = np.random.multivariate_normal(mu2, sigma2, n2)
            X3 = np.random.multivariate_normal(mu3, sigma3, n3)
            X = np.vstack([X1, X2, X3])
            np.random.shuffle(X)
            return X
        return sample_gmm(Ntrain), sample_gmm(Ntest)
    if name == 'moons':
        from sklearn.datasets import make_moons
        Xtrain, _ = make_moons(Ntrain, noise=0.1)
        Xtest, _ = make_moons(Ntest, noise=0.1)
        return Xtrain, Xtest
    if name == 'mnist0':
        X, y = get_mnist()
        # X = mnist['data'] / 255.0
        # y = mnist['target'].astype(int)
        X0 = X[y == 0]
        np.random.shuffle(X0)
        return X0[:Ntrain], X0[Ntrain:Ntrain + Ntest]
    if name == 'mnist01':
        X, y = get_mnist()
        # X = mnist['data'] / 255.0
        # y = mnist['target'].astype(int)
        X01 = X[(y == 0) | (y == 1)]
        np.random.shuffle(X01)
        return X01[:Ntrain], X01[Ntrain:Ntrain + Ntest]
    if name == 'mnist':
        X, y = get_mnist()
        # X = mnist['data'] / 255.0
        # y = mnist['target'].astype(int)
        np.random.seed(0)
        idx = np.random.permutation(len(X))
        X = X[idx]
        y = y[idx]
        return X[:Ntrain], X[Ntrain:Ntrain + Ntest]
    raise ValueError(f'Unknown dataset name {name}.')


# %%
datasets2d = ['normal2d', 'normal2d:squeeze', 'normal2d:squeeze:rotate', 'uniform2d', 'gmm2d3comp', 'moons']
datasetsNd = ['mnist0', 'mnist01', 'mnist']
N = 1000

def plot0(title, Xtrain, Xtest, halfHalf=False):
    plt.figure()
    plt.title(title)
    if halfHalf:
        Xtrain = Xtrain.reshape(-1, 2, Xtrain.shape[1]//2).mean(axis=-1)
        Xtest = Xtest.reshape(-1, 2, Xtest.shape[1]//2).mean(axis=-1)
    plt.scatter(Xtrain[:, 0], Xtrain[:, 1], marker='.', alpha=0.5, label='Train')
    plt.scatter(Xtest[:, 0], Xtest[:, 1], marker='.', alpha=0.5, label='Test')
    plt.axis('equal')
    plt.legend()
    pltshow()

for d in datasets2d:
    plot0(d, *get_dataset(d, N, N))

for d in datasetsNd:
    plot0(d, *get_dataset(d, N, N), halfHalf=True)

# %%

def get_fit_and_gen(dataset, model, Ntrain, Ntest, Ngen):
    Xtrain, Xtest = get_dataset(dataset, Ntrain, Ntest)
    model.fit(Xtrain)
    Xgen = model.sample(Ngen)
    # sklearn convention generates class too
    if isinstance(Xgen, tuple):
        Xgen = Xgen[0]
    return d2o(locals())

def plot1(title, res, halfHalf=False):
    W = -1
    try: # optional dependency
        import ot
        ntest = res.Xtest.shape[0]
        ngen = res.Xgen.shape[0]
        W = ot.emd(np.ones(ntest)/ntest, np.ones(ngen)/ngen,
                   ot.dist(res.Xtest, res.Xgen), log=True)[1]['cost']
    except:
        pass
    xtrain = res.Xtrain
    xtest = res.Xtest
    xgen = res.Xgen
    if halfHalf: # reduce to 2D by averaging half images
        xtrain = xtrain.reshape(-1, 2, xtrain.shape[1]//2).mean(axis=-1)
        xtest = xtest.reshape(-1, 2, xtest.shape[1]//2).mean(axis=-1)
        xgen = xgen.reshape(-1, 2, xgen.shape[1]//2).mean(axis=-1)
    plt.figure(figsize=(8, 4))
    plt.suptitle(f'{title}\nW={round(W, 3)}')
    plt.subplot(1, 2, 1)
    plt.title('Train / Test')
    plt.scatter(xtrain[:, 0], xtrain[:, 1], marker='.', alpha=0.5, label='Train')
    plt.scatter(xtest[:, 0], xtest[:, 1], marker='.', alpha=0.5, label='Test')
    plt.legend()
    plt.axis('equal')
    plt.subplot(1, 2, 2)
    plt.title('Test / Generated')
    plt.scatter(xtrain[:, 0], xtrain[:, 1], marker='.', alpha=0)
    plt.scatter(xtest[:, 0], xtest[:, 1], marker='.', alpha=0.5, label='Test')
    plt.scatter(xgen[:, 0], xgen[:, 1], marker='.', alpha=0.5, label='Generated')
    plt.axis('equal')
    pltshow()

# %%

# fit a GMM using sklearn, also PCA for comparison
from sklearn.decomposition import PCA
class PCAWrapper:
    def __init__(self, n_components):
        self.m = PCA(n_components=n_components, whiten=True) # so we get N(0,1) in latent space
    def fit(self, X):
        self.m.fit(X)
    def sample(self, N):
        return self.m.inverse_transform(np.random.normal(size=(N, self.m.n_components_)))

from sklearn.mixture import GaussianMixture
models = dict(
    gaussian_spherical=GaussianMixture(n_components=1, covariance_type='spherical'),
    gaussian_diag=GaussianMixture(n_components=1, covariance_type='diag'),
    gaussian_full=GaussianMixture(n_components=1, covariance_type='full'),
    gaussian9_diag=GaussianMixture(n_components=9, covariance_type='diag'),
    pca1=PCAWrapper(1),
    pca2=PCAWrapper(2),
)
models_base = models.copy()

#for d in ['normal2d', 'normal2d:squeeze:rotate', 'moons', 'uniform2d', 'normal2d:squeeze:rotate', 'gmm2d3comp', 'moons']:
for d in datasets2d:
    for k,model in models.items():
        plot1(f'{d} - {k}', get_fit_and_gen(d, model, N, N, N))

# %%

# now on MNIST

def plot2(title, res):
    # grid size (nb of images)
    W, H = 6, 4
    # grid of images
    plt.figure(figsize=(W*2, H*2))
    plt.suptitle(title)
    for i in range(W*H):
        plt.subplot(H, W, i+1)
        plt.imshow(res.Xgen[i].reshape(28, 28), vmin=0, vmax=1, cmap='gray')
        plt.axis('off')
    pltshow()

models['gaussian20_spherical'] = GaussianMixture(n_components=20, covariance_type='spherical')
models['gaussian20_diag'] = GaussianMixture(n_components=20, covariance_type='diag')
models['gaussian75_diag'] = GaussianMixture(n_components=75, covariance_type='diag')
models['pca20'] = PCAWrapper(20)
models_highdim = models.copy()

# now try on MNIST
for d in datasetsNd:
    for k,model in models.items():
        res = get_fit_and_gen(d, model, N, N, N)
        plot1(f'{d} - {k}', res, halfHalf=True)
        plot2(f'{d} - {k}', res)


#%%

# With PyTorch models

import torch
import torch.nn as nn
import torch.optim as optim

DEFAULT_ACT = nn.SiLU

class TorchMLPWrapper:
    def __init__(self, sizes=[2, 784], activation=DEFAULT_ACT, iterations=5000):
        self.iterations = iterations
        self.scale = 0.
        self.mean = 0.
        self.m = nn.Sequential()
        for i in range(len(sizes)-1):
            self.m.add_module(f'linear{i}', nn.Linear(sizes[i], sizes[i+1]))
            if i < len(sizes)-2:
                self.m.add_module(f'act{i}', activation())
    def fit(self, X):
        X = torch.tensor(X, dtype=torch.float32)
        #Z = X[:,0:1] * .5
        Z = torch.randn(size=(X.shape[0], self.m[0].in_features))
        Z.requires_grad = True
        optimizer1 = optim.Adam(self.m.parameters(), lr=0.001)
        optimizer2 = optim.Adam([Z], lr=0.01)
        for _iter in range(self.iterations):
            mask = torch.randperm(X.shape[0])[:128]
            X_gen = self.m(Z[mask])
            loss = nn.MSELoss()(X_gen, X[mask])
            loss.backward()
            optimizer1.step()
            optimizer2.step()
            optimizer1.zero_grad()
            optimizer2.zero_grad()
        self.mean = Z.mean().item()
        self.scale = Z.std().item()
    def sample(self, N):
        z = torch.randn(N, self.m[0].in_features) * self.scale + self.mean
        return self.m(z).detach().numpy()


models.clear()
models['linear_decoder'] = TorchMLPWrapper(sizes=[1, 2])
models['nonlinear_decoder'] = TorchMLPWrapper(sizes=[1, 16, 2])
models['wide_decoder'] = TorchMLPWrapper(sizes=[1, 1024, 2])
models['deep_decoder'] = TorchMLPWrapper(sizes=[1, 32, 32, 32,  2])

for d in ['normal2d:squeeze:rotate', 'gmm2d3comp', 'moons']:
    for k,model in models.items():
        res = get_fit_and_gen(d, model, N, N, N)
        plot1(f'{d} - {k}', res)

#%%

# now try on MNIST

models.clear()
models['linear_decoder'] = TorchMLPWrapper(sizes=[1, 784])
models['4linear_decoder'] = TorchMLPWrapper(sizes=[4, 784])
models['wide_decoder'] = TorchMLPWrapper(sizes=[1, 1024, 784]) # slow-ish

for d in datasetsNd:
    for k,model in models.items():
        res = get_fit_and_gen(d, model, N, N, N)
        plot1(f'{d} - {k}', res, halfHalf=True)
        plot2(f'{d} - {k}', res)


#%%

# Torch AutoEncoder

class TorchAutoEncoderWrapper:
    def __init__(self, sizes=[2, 784], activation=DEFAULT_ACT, iterations=5000):
        self.iterations = iterations
        self.scale = 0.
        self.mean = 0.
        self.enc = nn.Sequential()
        for i in range(len(sizes)-2, -1, -1):
            self.enc.add_module(f'enc{i}', nn.Linear(sizes[i+1], sizes[i]))
            self.enc.add_module(f'encact{i}', activation())
        self.dec = nn.Sequential()
        for i in range(len(sizes)-1):
            self.dec.add_module(f'dec{i}', nn.Linear(sizes[i], sizes[i+1]))
            if i < len(sizes)-2:
                self.dec.add_module(f'decact{i}', activation())
    def fit(self, X, **kwargs):
        X = torch.tensor(X, dtype=torch.float32)
        optimizer1 = optim.Adam(self.enc.parameters(), lr=0.001)
        optimizer2 = optim.Adam(self.dec.parameters(), lr=0.001)
        for _iter in range(self.iterations):
            mask = torch.randperm(X.shape[0])[:128]
            Z = self.enc(X[mask])
            X_gen = self.dec(Z)
            loss = nn.MSELoss()(X_gen, X[mask])
            loss.backward()
            optimizer1.step()
            optimizer2.step()
            optimizer1.zero_grad()
            optimizer2.zero_grad()
        Z = self.enc(X)
        self.mean = Z.mean().item()
        self.scale = Z.std().item()
        return Z.detach().numpy()
    def sample(self, N):
        z = torch.randn(N, self.dec[0].in_features) * self.scale + self.mean
        return self.dec(z).detach().numpy()

#%%

# on 2D datasets
models.clear()
models['linear_ae'] = TorchAutoEncoderWrapper(sizes=[1, 2])
models['nonlinear_ae'] = TorchAutoEncoderWrapper(sizes=[1, 16, 2])
models['wide_ae'] = TorchAutoEncoderWrapper(sizes=[1, 1024, 2])
models['deep_ae'] = TorchAutoEncoderWrapper(sizes=[1, 32, 32, 32,  2])

for d in ['normal2d:squeeze:rotate', 'gmm2d3comp', 'moons']:
    for k,model in models.items():
        res = get_fit_and_gen(d, model, N, N, N)
        plot1(f'{d} - {k}', res)

#%%

# on MNIST
models.clear()
models['linear_ae'] = TorchAutoEncoderWrapper(sizes=[4, 784])
models['nonlinear_ae'] = TorchAutoEncoderWrapper(sizes=[20, 64, 784])

for d in datasetsNd:
    for k,model in models.items():
        res = get_fit_and_gen(d, model, N, N, N)
        plot1(f'{d} - {k}', res, halfHalf=True)
        plot2(f'{d} - {k}', res)

#%%

# AutoEncoder + GMM in latent space

class AEGMMWrapper(TorchAutoEncoderWrapper):
    def __init__(self, sizes=[2, 784], activation=DEFAULT_ACT, iterations=5000, n_gmm_components=10):
        super().__init__(sizes=sizes, activation=activation, iterations=iterations)
        self.n_gmm_components = n_gmm_components
        self.gmm = GaussianMixture(n_components=n_gmm_components, covariance_type='diag')
    def fit(self, X):
        Z = super().fit(X)
        self.gmm.fit(Z)
    def sample(self, N):
        Z, _ = self.gmm.sample(N)
        z = torch.tensor(Z, dtype=torch.float32)
        return self.dec(z).detach().numpy()

#%%
# on MNIST directly
print(f'These {len(datasetsNd)} datasets, AE+GMM model, may take a while...')
models.clear()
models['ae_gmm'] = AEGMMWrapper(sizes=[4, 784], iterations=30000)

for d in datasetsNd:
    for k,model in models.items():
        res = get_fit_and_gen(d, model, N, N, N)
        plot1(f'{d} - {k}', res, halfHalf=True)
        plot2(f'{d} - {k}', res)

# %%


