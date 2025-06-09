export type PlantStage = 'seed' | 'sprout' | 'grow' | 'flower';

type PlantAnimationProps = {
  stage: PlantStage;
};

export default function PlantAnimation({ stage }: PlantAnimationProps) {
  const getImageSrc = (stage: PlantStage) => `/plants/${stage}.png`;
  const getAltText = (stage: PlantStage) => {
    switch (stage) {
      case 'seed':
        return '씨앗';
      case 'sprout':
        return '새싹';
      case 'grow':
        return '줄기';
      case 'flower':
        return '꽃';
    }
  };

  const getSizeClass = (stage: PlantStage) => {
    switch (stage) {
      case 'seed':
        return 'w-24 h-24';
      case 'sprout':
        return 'w-28 h-28';
      case 'grow':
        return 'w-32 h-32';
      case 'flower':
        return 'w-36 h-36';
    }
  };

  return (
    <div className="flex justify-center animate-fade-in">
      <img
        src={getImageSrc(stage)}
        alt={getAltText(stage)}
        className={`plant-img ${getSizeClass(stage)}`}
      />
    </div>
  );
}
