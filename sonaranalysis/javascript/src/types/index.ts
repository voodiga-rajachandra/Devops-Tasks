type ImageType = {
  mobile: string;
  desktop: string;
  alt: string;
};

type ButtonType = {
  label: string;
  href: string;
  target: string;
};

export type DataType = {
  image: ImageType;
  headline: string;
  author: string;
  date: string;
  button: ButtonType;
};
